package com.aag.crudkotlin.domain.exception

import java.lang.RuntimeException

class BookNotFoundException(message: String) : RuntimeException(message)