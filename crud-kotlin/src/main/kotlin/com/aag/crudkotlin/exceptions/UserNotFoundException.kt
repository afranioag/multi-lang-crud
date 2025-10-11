package com.aag.crudkotlin.exceptions

import java.lang.RuntimeException

class UserNotFoundException(message: String) : RuntimeException(message)