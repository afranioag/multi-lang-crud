package com.aag.crudkotlin.presentation.controller

import com.aag.crudkotlin.application.dto.request.BookRequest
import com.aag.crudkotlin.application.dto.response.PageResponse
import com.aag.crudkotlin.application.dto.response.BookResponse
import com.aag.crudkotlin.application.service.AuthService
import com.aag.crudkotlin.application.service.BookService
import com.aag.crudkotlin.infrastructure.security.RequireAdmin
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/books")
class BookController(
    private val authService: AuthService,
    private val bookService: BookService) {

    @PostMapping("/{id}")
    fun addBook(@PathVariable id: Long, @Valid @RequestBody book: BookRequest): BookResponse {
        return bookService.addBook(id, book);
    }

    @GetMapping("/{id}")
    fun getById(
        request: HttpServletRequest,
        @PathVariable id: Long): BookResponse {
        val email = request.getAttribute("userEmail") as String
        return bookService.getBook(email, id);
    }

    @GetMapping("/mybooks")
    fun getMyBooks(
        request: HttpServletRequest,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): PageResponse<BookResponse> {
        val email = request.getAttribute("userEmail") as String
        return bookService.gelMyAllBooks(email, page, size)
    }

    @RequireAdmin
    @GetMapping("/all")
    fun getAllBooks(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): PageResponse<BookResponse> {
        return bookService.gelAllBooks( page, size)
    }
}