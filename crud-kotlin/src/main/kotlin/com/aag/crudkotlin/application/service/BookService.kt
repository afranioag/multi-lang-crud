package com.aag.crudkotlin.application.service

import com.aag.crudkotlin.application.dto.request.BookRequest
import com.aag.crudkotlin.application.dto.response.PageResponse
import com.aag.crudkotlin.application.dto.response.BookResponse
import com.aag.crudkotlin.domain.entity.Book
import com.aag.crudkotlin.domain.exception.AddressNotFoundException
import com.aag.crudkotlin.domain.exception.BookNotFoundException
import com.aag.crudkotlin.domain.exception.UserNotFoundException
import com.aag.crudkotlin.domain.repository.BookRepository
import com.aag.crudkotlin.domain.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class BookService(val bookRepository: BookRepository,
    val userRepository: UserRepository
) {

    fun addBook(id: Long, bookRequest: BookRequest) : BookResponse {
        val user = userRepository.findById(id)
            .orElseThrow {UserNotFoundException("User whit id $id not found!")};

        val book = Book(
            title = bookRequest.title,
            author = bookRequest.author,
            isbn = bookRequest.isbn,
            description = bookRequest.description,
            publishedYear = bookRequest.publishedYear,
            language = bookRequest.language,
            genre = bookRequest.genre,
            edition = bookRequest.edition,
            user = user
        )

        bookRepository.save(book)

        return BookResponse(
            title = book.title,
            author = book.author,
            description = book.description,
            genre = book.genre,
            edition = book.edition
        )
    }

    fun getBook(email: String, id: Long): BookResponse {
        val book = bookRepository.findByIdAndUserEmail(id, email)
            .orElseThrow { BookNotFoundException("Book with id $id not found!")}

        return BookResponse(
            title = book.title,
            author = book.author,
            description = book.description,
            genre = book.genre,
            edition = book.edition
        )
    }

    fun gelMyAllBooks(email: String, pageNumber: Int, itemsPerPage: Int): PageResponse<BookResponse> {

        val pageable: Pageable = Pageable.ofSize(itemsPerPage).withPage(pageNumber);
        val page = bookRepository.findAllByUserEmail(email, pageable)
        return buildBooksPage(page)
    }

    @Transactional
    fun removeBook(userId: Long, addressId: Long) {
        val user = userRepository.findById(userId)
            .orElseThrow {UserNotFoundException("User whit id $userId not found!")};

        user.books.find { it.id == addressId }
            ?: throw UserNotFoundException("Book whit id $addressId not found for user with id $userId")

        val addressRemoved = user.books.removeIf { it.id == addressId }

        if (!addressRemoved) {
            throw AddressNotFoundException("Book whit id $addressId not removed!")
        }

        userRepository.save(user)
    }

    // ROTAS PARA ADMIN
    fun gelAllBooks(pageNumber: Int, itemsPerPage: Int): PageResponse<BookResponse> {

        val pageable: Pageable = Pageable.ofSize(itemsPerPage).withPage(pageNumber);
        val page = bookRepository.findAll(pageable)
        return buildBooksPage(page)
    }

    fun buildBooksPage(page: Page<Book>) : PageResponse<BookResponse> {
        val responses = page.content.stream().map { book -> BookResponse(
            title = book.title,
            author = book.author,
            description = book.description,
            genre = book.genre,
            edition = book.edition,
        ) }.collect(Collectors.toList())

        return PageResponse(
            totalPages = page.totalPages,
            totalElements = page.totalElements,
            size = page.size,
            pageNumber = page.number,
            numberOfElements = page.numberOfElements,
            content = responses
        )
    }
}