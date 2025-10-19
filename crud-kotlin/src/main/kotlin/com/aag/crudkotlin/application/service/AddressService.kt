package com.aag.crudkotlin.application.service

import com.aag.crudkotlin.application.dto.request.AddressRequest
import com.aag.crudkotlin.domain.entity.Address
import com.aag.crudkotlin.domain.entity.User
import com.aag.crudkotlin.domain.repository.AddressRepository
import org.springframework.stereotype.Service

@Service
class AddressService (private val addressRepository: AddressRepository) {

    fun save(addressRequest: AddressRequest, user: User) : Address {
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

        return addressRepository.save(address)
    }
}
