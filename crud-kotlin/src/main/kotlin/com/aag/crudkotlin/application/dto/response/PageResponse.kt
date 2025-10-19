package com.aag.crudkotlin.application.dto.response

data class PageResponse<T> (
    var totalPages: Int = 0,
    var totalElements: Long = 0,
    var size: Int = 0,
    var pageNumber: Int = 0,
    var numberOfElements: Int = 0,
    var content: List<T> = emptyList()
)