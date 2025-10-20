package com.aag.crudkotlin.presentation.controller

import com.aag.crudkotlin.application.dto.request.AddressRequest
import com.aag.crudkotlin.application.dto.request.UserRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `deve criar usuario com sucesso`() {
        val userRequest = UserRequest(
            email = "teste@email.com",
            password = "Senha123",
            name = "Teste User",
            age = 25,
            document = "12345678900",
            address = AddressRequest(
                dressCode = "12345-678",
                street = "Rua Teste",
                number = 100,
                complement = "Apto 1",
                city = "São Paulo",
                state = "SP",
                country = "Brasil"
            )
        )

        mockMvc.perform(
            post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value("Teste User"))
            .andExpect(jsonPath("$.email").value("teste@email.com"))
    }

    @Test
    fun `deve retornar erro quando email estiver vazio`() {
        val userRequest = UserRequest(
            email = "",
            password = "Senha123",
            name = "Teste User",
            age = 25,
            document = "12345678900"
        )

        mockMvc.perform(
            post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest))
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.message").exists())
    }

    @Test
    fun `deve retornar erro quando password estiver menor que o aceitavel`() {
        val userRequest = UserRequest(
            email = "user@email.com",
            password = "S",
            name = "Teste User",
            age = 25,
            document = "12345678900"
        )

        mockMvc.perform(
            post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest))
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.message").exists())
    }
}