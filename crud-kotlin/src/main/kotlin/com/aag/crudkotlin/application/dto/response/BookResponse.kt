package com.aag.crudkotlin.application.dto.response

data class BookResponse (
    var title: String,
    var author: String,
    var description: String? = null,
    var genre: String? = null,
    var edition: Int,
)