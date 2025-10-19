package com.aag.crudkotlin.domain.repository

import com.aag.crudkotlin.domain.entity.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface BookRepository : JpaRepository<Book, Long> {
    fun findAllByUserEmail(userEmail: String, pageable: Pageable): Page<Book>
    fun findByIdAndUserEmail(id: Long, userEmail: String): Optional<Book>
}