package com.aag.crudkotlin.application.service

import com.aag.crudkotlin.application.dto.request.AddressRequest
import com.aag.crudkotlin.application.dto.request.UserRequest
import com.aag.crudkotlin.application.dto.response.UserResponse
import com.aag.crudkotlin.domain.entity.User
import com.aag.crudkotlin.domain.entity.UserRole
import com.aag.crudkotlin.domain.exception.AddressNotFoundException
import com.aag.crudkotlin.domain.exception.InvalidPasswordException
import com.aag.crudkotlin.domain.exception.ObjectAlreadyExistsException
import com.aag.crudkotlin.domain.exception.UserNotFoundException
import com.aag.crudkotlin.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

        if(userRequest.address != null) {
            addressService.save(userRequest.address, user)
        }

        return UserResponse(user.id!!, user.name, user.age, user.document);
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

    fun addAddress(id: Long, addressRequest: AddressRequest) {
        val user = userRepository.findById(id)
            .orElseThrow {UserNotFoundException("User whit id $id not found!")};

        addressService.save(addressRequest, user)
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
}