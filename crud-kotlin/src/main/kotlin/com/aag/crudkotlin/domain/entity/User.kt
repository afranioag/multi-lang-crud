package com.aag.crudkotlin.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
@EntityListeners(UserEntityListener::class)
data class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(name = "password")
    var password: String,

    @Enumerated(EnumType.STRING)
    val role: UserProfile,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var age: Int,

    @Column(nullable = false, unique = true)
    var document: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    val addresses: MutableList<Address> = mutableListOf()
)