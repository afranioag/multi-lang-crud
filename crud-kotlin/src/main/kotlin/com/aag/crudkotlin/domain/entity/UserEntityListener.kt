package com.aag.crudkotlin.domain.entity

import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import org.springframework.security.crypto.bcrypt.BCrypt

class UserEntityListener {
    @PrePersist
    @PreUpdate
    fun hashPassword(user: User) {
        // Só faz hash se a senha não estiver hasheada ainda
        if (!user.password.startsWith("\$2a\$") && !user.password.startsWith("\$2b\$")) {
            user.password = BCrypt.hashpw(user.password, BCrypt.gensalt())
        }
    }
}