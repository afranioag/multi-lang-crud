package com.aag.crudkotlin.domain.exception

import java.lang.RuntimeException

class InvalidPasswordException(message: String) : RuntimeException(message)