package com.aag.crudkotlin.repositories

import com.aag.crudkotlin.entities.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<Address, Long> {
    fun id(id: Long): MutableList<Address>
}