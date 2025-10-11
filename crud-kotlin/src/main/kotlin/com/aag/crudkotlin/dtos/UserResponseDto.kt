package com.aag.crudkotlin.dtos

data class UserResponseDto(
    val id: Long,
    val name: String,
    val age: Int,
    val document: String,
    val addresses: List<AddressDto>
)