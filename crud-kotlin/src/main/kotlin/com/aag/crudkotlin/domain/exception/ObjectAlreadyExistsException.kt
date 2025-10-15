package com.aag.crudkotlin.domain.exception

import java.lang.RuntimeException

class ObjectAlreadyExistsException(message: String) : RuntimeException(message)