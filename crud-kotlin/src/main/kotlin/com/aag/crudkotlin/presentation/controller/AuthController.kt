package com.aag.crudkotlin.presentation.controller

import com.aag.crudkotlin.application.dto.request.LoginRequest
import com.aag.crudkotlin.application.dto.response.LoginResponse
import com.aag.crudkotlin.application.service.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping( "/v1/auth")
class AuthController (private val authService: AuthService) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest) : LoginResponse {
        return authService.login(loginRequest)
    }
}