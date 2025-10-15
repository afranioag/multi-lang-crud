package com.aag.crudkotlin.domain.exception

import java.lang.RuntimeException

class UserNotFoundException(message: String) : RuntimeException(message)