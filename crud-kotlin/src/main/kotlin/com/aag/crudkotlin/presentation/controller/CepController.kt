package com.aag.crudkotlin.presentation.controller

import com.aag.crudkotlin.application.dto.response.CepResponse
import com.aag.crudkotlin.application.service.CepService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CepController(private val cepService: CepService) {

    @GetMapping("/locale/{cep}")
    fun getLocale(@PathVariable cep: String) : ResponseEntity<CepResponse> {
        val locale = cepService.findLocale(cep)
        return ResponseEntity.ok(locale)

    }
}