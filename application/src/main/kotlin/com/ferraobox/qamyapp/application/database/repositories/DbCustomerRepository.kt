package com.ferraobox.qamyapp.application.database.repositories

import com.ferraobox.qamyapp.application.database.entities.CustomerDb
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DbCustomerRepository : JpaRepository<CustomerDb?, Long?> {
    fun existsByEmail(email: String?): Boolean
    fun findByEmail(email: String?): Optional<CustomerDb?>?
}