package com.aag.crudkotlin.services

import com.aag.crudkotlin.dtos.AddressDto
import com.aag.crudkotlin.entities.Address
import com.aag.crudkotlin.entities.User
import com.aag.crudkotlin.repositories.AddressRepository
import org.springframework.stereotype.Service

@Service
class AddressService (private val addressRepository: AddressRepository) {

    fun save(addressDto: AddressDto, user: User) : Address {
        val address = Address (
            dressCode = addressDto.dressCode,
            street = addressDto.street,
            number = addressDto.number,
            complement = addressDto.complement,
            city = addressDto.city,
            state = addressDto.state,
            country = addressDto.country,
            user = user
        )

        return addressRepository.save(address)
    }
}