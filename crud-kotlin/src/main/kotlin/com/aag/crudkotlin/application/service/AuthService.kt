package com.aag.crudkotlin.application.service

import com.aag.crudkotlin.application.dto.request.LoginRequest
import com.aag.crudkotlin.application.dto.response.LoginResponse
import com.aag.crudkotlin.application.dto.response.UserResponse
import com.aag.crudkotlin.domain.entity.User
import com.aag.crudkotlin.domain.entity.UserRole
import com.aag.crudkotlin.domain.exception.ForbiddenException
import com.aag.crudkotlin.domain.exception.InvalidCredentialsException
import com.aag.crudkotlin.domain.exception.UserNotFoundException
import com.aag.crudkotlin.domain.repository.UserRepository
import com.aag.crudkotlin.infrastructure.security.JwtUtil
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class AuthService (
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil) {

    fun login(loginRequest: LoginRequest) : LoginResponse {
        val user = userRepository.findByEmail(loginRequest.email)
            .orElseThrow { UserNotFoundException("User not found!") }

        val passValid = BCrypt.checkpw(loginRequest.password, user.password)
        if (!passValid) {
            throw InvalidCredentialsException("Invalid username or password")
        }

        val token = jwtUtil.generateToken(email = user.email, role = user.role)
        return LoginResponse(token = token)
    }

    fun getLoggedUser(email: String): User {
        val user = userRepository.findByEmail(email)
            .orElseThrow { UserNotFoundException("User not found!") }
        return user;
    }

    fun isAdmin(user: User) : Boolean {
        return UserRole.ADMIN == user.role
    }

    fun isAwner(user: User, id: Long) : Boolean {
        return user.id == id
    }

    fun validateAuthorization(request: HttpServletRequest, id: Long) {
        val email = request.getAttribute("userEmail") as String
        val user = getLoggedUser(email)

        if(!isAdmin(user) && !isAwner(user, id)) {
            throw ForbiddenException("Forbidden")
        }
    }
}