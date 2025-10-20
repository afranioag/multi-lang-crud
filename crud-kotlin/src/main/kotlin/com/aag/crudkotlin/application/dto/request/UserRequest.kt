package com.aag.crudkotlin.application.dto.request

import jakarta.validation.constraints.NotBlank

data class UserRequest (

    @field:NotBlank(message = "Email cannot be empty")
    val email: String,

    @field:NotBlank(message = "Password cannot be empty")
    val password: String,

    val name: String,

    val age: Int,

    val document: String,

    val address: AddressRequest? = null
)