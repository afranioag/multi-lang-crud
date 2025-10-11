package com.aag.crudkotlin.dtos

data class AddressDto (
    val id: Long,
    val dressCode: String,
    val street: String,
    val number: Int,
    val complement: String,
    val city: String,
    val state: String,
    val country: String

)