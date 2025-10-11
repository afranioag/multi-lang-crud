package com.aag.crudkotlin.dtos

data class UserDto (
    val name: String,
    val age: Int,
    val document: String,
    val address: AddressDto? = null
)