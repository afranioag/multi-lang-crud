package com.aag.crudkotlin.application.service

import com.aag.crudkotlin.application.dto.request.LoginRequest
import com.aag.crudkotlin.application.dto.response.LoginResponse
import com.aag.crudkotlin.domain.exception.InvalidCredentialsException
import com.aag.crudkotlin.domain.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class AuthService (private val userRepository: UserRepository) {

    fun login(loginRequest: LoginRequest) : LoginResponse {
        val user = userRepository.findByEmail(loginRequest.email)

        // 2. Validar senha (compara texto plano com hash)
        val senhaValida = BCrypt.checkpw(loginRequest.password, user.password)

        if (!senhaValida) {
            throw InvalidCredentialsException("Email ou senha inválidos")
        }

        // 3. Gerar token JWT (faremos no próximo passo)
        val token = "TOKEN_AQUI"  // temporário

        return LoginResponse(token = token)
    }
}