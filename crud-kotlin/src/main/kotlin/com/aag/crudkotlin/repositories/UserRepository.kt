package com.aag.crudkotlin.repositories

import com.aag.crudkotlin.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun id(id: Long): MutableList<User>
}