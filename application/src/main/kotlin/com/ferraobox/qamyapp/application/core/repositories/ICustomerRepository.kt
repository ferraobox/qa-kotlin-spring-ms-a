package com.ferraobox.qamyapp.application.core.repositories

import com.ferraobox.qamyapp.application.core.domain.Customer
import java.util.*

interface ICustomerRepository {
    fun persist(customer: Customer): Customer
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Optional<Customer>
    fun findById(id: Long): Optional<Customer>
}