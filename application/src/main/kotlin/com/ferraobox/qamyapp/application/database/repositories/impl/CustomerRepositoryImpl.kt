package com.ferraobox.qamyapp.application.database.repositories.impl

import com.ferraobox.qamyapp.application.core.domain.Customer
import com.ferraobox.qamyapp.application.core.mappers.CustomerDomainDbMapper
import com.ferraobox.qamyapp.application.core.mappers.CustomerDomainDbMapper.mapToDb
import com.ferraobox.qamyapp.application.core.mappers.CustomerDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.core.mappers.OrderDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.core.repositories.ICustomerRepository
import com.ferraobox.qamyapp.application.database.entities.CustomerDb
import com.ferraobox.qamyapp.application.database.repositories.DbCustomerRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Repository
open class CustomerRepositoryImpl(
    private val repository: DbCustomerRepository,
) : ICustomerRepository {

    override fun persist(customer: Customer): Customer {
        val customerData: CustomerDb = customer.mapToDb()
        return repository.save(customerData).mapToDomain()
    }

    override fun existsByEmail(email: String): Boolean {
        return repository.existsByEmail(email)
    }

    override fun findByEmail(email: String): Optional<Customer> {
        return repository
            .findByEmail(email)
            .map { c -> c?.mapToDomain() }
    }

    override fun findById(id: Long): Optional<Customer> {
        return repository
            .findById(id)
            .map { c -> c?.mapToDomain() }
    }
}