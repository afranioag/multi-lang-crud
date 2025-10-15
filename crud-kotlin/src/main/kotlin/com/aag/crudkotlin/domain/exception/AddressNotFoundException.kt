package com.aag.crudkotlin.domain.exception

import java.lang.RuntimeException

class AddressNotFoundException(message: String) : RuntimeException(message)