package com.aag.crudkotlin.infrastructure.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(
    private val jwtUtil: JwtUtil
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = extractTokenFromRequest(request)
        if (token == null) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write("{\"error\": \"Token não fornecido\"}")
            return
        }

        try {
            if (jwtUtil.validateToken(token)) {
                val email = jwtUtil.extractEmail(token)
                val role = jwtUtil.extractRole(token)

                request.setAttribute("userEmail", email)
                request.setAttribute("userRole", role)
            } else {
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.writer.write("{\"error\": \"Token inválido\"}")
                return
            }
        } catch (e: Exception) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write("{\"error\": \"Token inválido ou expirado\"}")
            return
        }

        filterChain.doFilter(request, response)
    }

    private fun extractTokenFromRequest(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization")

        return if (authHeader != null && authHeader.startsWith("Bearer ")) {
            authHeader.substring(7) // Remove "Bearer "
        } else {
            null
        }
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val path = request.requestURI
        return path.startsWith("/v1/auth/") ||
                path.startsWith("/v1/users") && request.method == "POST" ||
                path.startsWith("/h2-console") ||
                path.startsWith("/cep/")
    }
}