package com.aag.crudkotlin.infrastructure.client

import com.aag.crudkotlin.application.dto.response.CepResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    name = "\${cep.api.name}",
    url = "\${cep.api.url}"
)
interface CepClient {
    @GetMapping("/{cep}/json")
    fun buscarCep(@PathVariable cep: String): CepResponse
}