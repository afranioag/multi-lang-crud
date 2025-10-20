package com.aag.crudkotlin.application.service

import com.aag.crudkotlin.application.dto.request.AddressRequest
import com.aag.crudkotlin.application.dto.request.UserRequest
import com.aag.crudkotlin.application.dto.response.AddressResponse
import com.aag.crudkotlin.application.dto.response.PageResponse
import com.aag.crudkotlin.application.dto.response.UserResponse
import com.aag.crudkotlin.domain.entity.User
import com.aag.crudkotlin.domain.entity.UserRole
import com.aag.crudkotlin.domain.exception.AddressNotFoundException
import com.aag.crudkotlin.domain.exception.InvalidPasswordException
import com.aag.crudkotlin.domain.exception.ObjectAlreadyExistsException
import com.aag.crudkotlin.domain.exception.UserNotFoundException
import com.aag.crudkotlin.domain.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class UserService (private val userRepository: UserRepository,
    private val addressService: AddressService) {

    fun creatUser (userRequest: UserRequest) : UserResponse {

        isPasswordValid(userRequest.password)

        if(userRepository.existsByDocument(userRequest.document)) {
            throw ObjectAlreadyExistsException("User with document ${userRequest.document} already exists!")
        }

        var user = User (
            email = userRequest.email,
            password = userRequest.password,
            role = UserRole.SIMPLE,
            name = userRequest.name,
            age = userRequest.age,
            document = userRequest.document
        )

        user = userRepository.save(user)

        val response = UserResponse(user.id!!, user.name, user.age, user.document)
        if(userRequest.address != null) {
            response.address =  addressService.save(userRequest.address, user)
        }

        return response
    }

    fun getUser(id: Long): UserResponse {
        val user = userRepository.findById(id)
            .orElseThrow { UserNotFoundException("User whit id $id not found!") }

        return UserResponse(
            id = user.id!!,
            name = user.name,
            age = user.age,
            document = user.document
        )
    }

    @Transactional
    fun updateUser(id: Long, userRequest: UserRequest) {
        val user = userRepository.findById(id)
            .orElseThrow {UserNotFoundException("User whit id $id not found!")};

        user.name = userRequest.name
        user.age = userRequest.age
        user.document = userRequest.document
        userRepository.save(user)
    }

    @Transactional
    fun remove(id: Long) {
        userRepository.deleteById(id);
    }

    fun isPasswordValid(password: String) {
        val passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{6,}$".toRegex()

        if (!passwordRegex.matches(password)) {
            throw InvalidPasswordException("Password invalid!")
        }
    }

    fun getAllUsers(pageNumber: Int, itemsPerPage: Int): PageResponse<UserResponse> {
        val pageable: Pageable = Pageable.ofSize(itemsPerPage).withPage(pageNumber);
        val page = userRepository.findAll(pageable)

        val responses = page.content.stream().map { user -> UserResponse(user.id!!, user.name, user.age, user.document) }
        .collect(Collectors.toList())

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