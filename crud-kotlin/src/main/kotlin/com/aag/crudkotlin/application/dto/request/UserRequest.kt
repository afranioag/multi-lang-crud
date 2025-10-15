package com.aag.crudkotlin.application.dto.request

import jakarta.validation.constraints.NotEmpty

data class UserRequest (

    @field:NotEmpty(message = "Email cannot be empty")
    val email: String,

    @field:NotEmpty(message = "Password cannot be empty")
    val password: String,

    val name: String,

    val age: Int,

    val document: String,

    val address: AddressRequest? = null
)