package com.aag.crudkotlin.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "address")
data class Address (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val dressCode: String,
    val street: String,
    val number: Int,
    val complement: String,
    val city: String,
    val state: String,
    val country: String,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
)