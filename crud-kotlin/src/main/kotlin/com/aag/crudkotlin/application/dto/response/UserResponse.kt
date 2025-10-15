package com.aag.crudkotlin.application.dto.response

import com.aag.crudkotlin.application.dto.request.AddressRequest

data class UserResponse(
    val id: Long,
    val name: String,
    val age: Int,
    val document: String,
    val addresses: List<AddressRequest>
)