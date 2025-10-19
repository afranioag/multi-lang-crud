package com.aag.crudkotlin.application.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class BookRequest (
    @field:NotBlank(message = "Title is required")
    @param:NotBlank(message = "Title is required")
    var title: String,

    @field:NotBlank(message = "Author is required")
    var author: String,

    var isbn: String? = null,
    var publisher: String? = null,
    var publishedYear: Int? = null,
    var description: String? = null,
    var pages: Int? = null,
    var language: String? = null,
    var genre: String? = null,
    @field:NotNull(message = "Edition is required")
    var edition: Int,
)