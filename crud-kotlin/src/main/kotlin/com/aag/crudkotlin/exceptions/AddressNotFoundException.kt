package com.aag.crudkotlin.exceptions

import java.lang.RuntimeException

class AddressNotFoundException(message: String) : RuntimeException(message)