package com.aag.crudkotlin.application.service

import com.aag.crudkotlin.application.dto.request.LoginRequest
import com.aag.crudkotlin.application.dto.response.LoginResponse
import com.aag.crudkotlin.domain.exception.InvalidCredentialsException
import com.aag.crudkotlin.domain.repository.UserRepository
import com.aag.crudkotlin.infrastructure.security.JwtUtil
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class AuthService (private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil) {

    fun login(loginRequest: LoginRequest) : LoginResponse {
        val user = userRepository.findByEmail(loginRequest.email)
        val passValid = BCrypt.checkpw(loginRequest.password, user.password)
        if (!passValid) {
            throw InvalidCredentialsException("Invalid username or password")
        }

        val token = jwtUtil.generateToken(email = user.email, role = user.role)
        return LoginResponse(token = token)
    }
}