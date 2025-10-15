package com.aag.crudkotlin.domain.exception

import java.lang.RuntimeException

class InvalidCredentialsException(message: String) : RuntimeException(message)