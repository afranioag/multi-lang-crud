package com.aag.crudkotlin.application.dto.request

data class AddressRequest (
    val dressCode: String,
    val street: String,
    val number: Int,
    val complement: String,
    val city: String,
    val state: String,
    val country: String

)