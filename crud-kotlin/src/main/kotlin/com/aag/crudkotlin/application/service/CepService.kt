package com.aag.crudkotlin.application.service

import com.aag.crudkotlin.application.dto.response.CepResponse
import com.aag.crudkotlin.infrastructure.client.CepClient
import org.springframework.stereotype.Service

@Service
class CepService(private val cepClient: CepClient) {
    fun findLocale(cep: String) : CepResponse {
        val cep = cep.replace("-", "").replace(".", "");
        if(cep.length != 8) {
            throw IllegalArgumentException("Invalid CEP format")
        }
        return cepClient.buscarCep(cep)
    }
}