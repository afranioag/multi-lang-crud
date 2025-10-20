package com.aag.crudkotlin.infrastructure.security

import com.aag.crudkotlin.domain.entity.UserRole
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

@Component
class RoleInterceptor : HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        if (handler is HandlerMethod) {
            val requireAdmin = handler.getMethodAnnotation(RequireAdmin::class.java)

            if (requireAdmin != null) {
                val role = request.getAttribute("userRole") as? UserRole

                if (role != UserRole.ADMIN) {
                    response.status = HttpServletResponse.SC_FORBIDDEN
                    response.writer.write("{\"error\": \"Acesso negado. Apenas ADMIN.\"}")
                    return false
                }
            }
        }

        return true
    }
}