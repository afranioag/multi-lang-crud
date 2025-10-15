package com.aag.crudkotlin.presentation.handler

data class ErrorResponse (
    val status: Int,
    val message: String
)