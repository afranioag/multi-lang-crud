package com.aag.crudkotlin.application.dto.response

data class UserResponse(
    var id: Long,
    var name: String,
    var email: String,
    var age: Int,
    var document: String,
    var address: AddressResponse? = null,
)