package com.aag.crudkotlin.domain.repository

import com.aag.crudkotlin.domain.entity.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<Address, Long> {
    fun id(id: Long): MutableList<Address>
}