package com.aag.crudkotlin.services

import com.aag.crudkotlin.dtos.AddressDto
import com.aag.crudkotlin.dtos.UserDto
import com.aag.crudkotlin.dtos.UserResponseDto
import com.aag.crudkotlin.entities.User
import com.aag.crudkotlin.exceptions.AddressNotFoundException
import com.aag.crudkotlin.exceptions.UserNotFoundException
import com.aag.crudkotlin.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository,
    private val addressService: AddressService) {

    fun creatUser (userDto: UserDto) : User {

        var user = User (
            name = userDto.name,
            age = userDto.age,
            document = userDto.document
        )

        user = userRepository.save(user)

        if(userDto.address != null) {
            addressService.save(userDto.address, user)
        }

        return user;
    }

    fun getUser(id: Long): UserResponseDto {
        val user = userRepository.findById(id)
            .orElseThrow { UserNotFoundException("Usuário com id $id não encontrado") }

        return UserResponseDto(
            id = user.id!!,
            name = user.name,
            age = user.age,
            document = user.document,
            addresses = user.addresses.map { address ->
                AddressDto(
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

    fun updateUser(id: Long, userDto: UserDto) {
        var user = userRepository.findById(id)
            .orElseThrow {UserNotFoundException("Usuário com id $id não encontrado")};

        user.name = userDto.name
        user.age = userDto.age
        user.document = userDto.document
        userRepository.save(user)
    }

    fun addAddress(id: Long, addressDto: AddressDto) {
        val user = userRepository.findById(id)
            .orElseThrow {UserNotFoundException("Usuário com id $id não encontrado")};

        addressService.save(addressDto, user)
    }

    fun remove(id: Long) {
        userRepository.deleteById(id);
    }

    fun removeAddress(userId: Long, addressId: Long) {
        val user = userRepository.findById(userId)
            .orElseThrow {UserNotFoundException("Usuário com id $userId não encontrado")};

        user.addresses.find { it.id == addressId }
            ?: throw UserNotFoundException("Endereço com id $addressId não encontrado para o usuário com id $userId")

        val addressRemoved = user.addresses.removeIf { it.id == addressId }

        if (!addressRemoved) {
            throw AddressNotFoundException("Endereço com id $addressId não encontrado")
        }

        userRepository.save(user)

    }
}