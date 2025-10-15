package com.aag.crudkotlin.application.dto.request

data class UserRequest (
    val name: String,
    val age: Int,
    val document: String,
    val address: AddressRequest? = null
)