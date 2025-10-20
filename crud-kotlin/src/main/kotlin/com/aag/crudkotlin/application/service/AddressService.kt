package com.aag.crudkotlin.application.service

import com.aag.crudkotlin.application.dto.request.AddressRequest
import com.aag.crudkotlin.application.dto.response.AddressResponse
import com.aag.crudkotlin.domain.entity.Address
import com.aag.crudkotlin.domain.entity.User
import com.aag.crudkotlin.domain.exception.UserNotFoundException
import com.aag.crudkotlin.domain.repository.AddressRepository
import org.springframework.stereotype.Service

@Service
class AddressService (private val addressRepository: AddressRepository) {

    fun save(addressRequest: AddressRequest, user: User) : AddressResponse {
        var address = Address (
            dressCode = addressRequest.dressCode,
            street = addressRequest.street,
            number = addressRequest.number,
            complement = addressRequest.complement,
            city = addressRequest.city,
            state = addressRequest.state,
            country = addressRequest.country,
            user = user
        )

        address = addressRepository.save(address)
        return AddressResponse(
            id = address.id!!,
            dressCode = address.dressCode,
            street = address.street,
            number = address.number,
            complement = address.complement,
            city = address.city,
            state = address.state,
            country = address.country,
        )
    }

    fun addAddress(user: User, addressRequest: AddressRequest) {
        val address = Address (
            dressCode = addressRequest.dressCode,
            street = addressRequest.street,
            number = addressRequest.number,
            complement = addressRequest.complement,
            city = addressRequest.city,
            state = addressRequest.state,
            country = addressRequest.country,
            user = user
        )
        addressRepository.save(address)
    }
}
