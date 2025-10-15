package com.aag.crudkotlin.application.service

import com.aag.crudkotlin.application.dto.request.AddressRequest
import com.aag.crudkotlin.application.dto.request.UserRequest
import com.aag.crudkotlin.application.dto.response.UserResponse
import com.aag.crudkotlin.domain.entity.User
import com.aag.crudkotlin.domain.exception.AddressNotFoundException
import com.aag.crudkotlin.domain.exception.ObjectAlreadyExistsException
import com.aag.crudkotlin.domain.exception.UserNotFoundException
import com.aag.crudkotlin.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService (private val userRepository: UserRepository,
    private val addressService: AddressService) {

    fun creatUser (userRequest: UserRequest) : User {
        if(userRepository.existsByDocument(userRequest.document)) {
            throw ObjectAlreadyExistsException("User with document ${userRequest.document} already exists!")
        }

        var user = User (
            name = userRequest.name,
            age = userRequest.age,
            document = userRequest.document
        )

        user = userRepository.save(user)

        if(userRequest.address != null) {
            addressService.save(userRequest.address, user)
        }

        return user;
    }

    fun getUser(id: Long): UserResponse {
        val user = userRepository.findById(id)
            .orElseThrow { UserNotFoundException("Usuário com id $id não encontrado") }

        return UserResponse(
            id = user.id!!,
            name = user.name,
            age = user.age,
            document = user.document,
            addresses = user.addresses.map { address ->
                AddressRequest(
                    id = address.id!!,
                    dressCode = address.dressCode,
                    street = address.street,
                    number = address.number,
                    complement = address.complement,
                    city = address.city,
                    state = address.state,
                    country = address.country
                )
            }
        )
    }

    @Transactional
    fun updateUser(id: Long, userRequest: UserRequest) {
        var user = userRepository.findById(id)
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

    @Transactional
    fun removeAddress(userId: Long, addressId: Long) {
        val user = userRepository.findById(userId)
            .orElseThrow {UserNotFoundException("User whit id $userId not found!")};

        user.addresses.find { it.id == addressId }
            ?: throw UserNotFoundException("Address whit id $addressId not found for user with id $userId")

        val addressRemoved = user.addresses.removeIf { it.id == addressId }

        if (!addressRemoved) {
            throw AddressNotFoundException("Address whit id $addressId not removed!")
        }

        userRepository.save(user)

    }
}