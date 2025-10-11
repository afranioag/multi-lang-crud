package com.aag.crudkotlin.exceptions

data class ErrorResponse (
    val status: Int,
    val message: String
)