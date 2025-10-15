package com.aag.crudkotlin.domain.repository

import com.aag.crudkotlin.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun existsByDocument(document: String) : Boolean
    fun findByEmail(email: String) : User
}