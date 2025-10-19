package com.aag.crudkotlin.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "books")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var author: String,
    var isbn: String? = null,
    var publisher: String? = null,
    var publishedYear: Int? = null,

    @Column(length = 500)
    var description: String? = null,
    var pages: Int? = null,
    var language: String? = null,
    var genre: String? = null,
    var edition: Int,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
)