package com.ferraobox.qamyapp.application.core.mappers

import com.ferraobox.qamyapp.application.core.domain.Customer
import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.database.entities.CustomerDb
import java.util.*


object CustomerDomainDbMapper {

    fun Customer.mapToDb(): CustomerDb {
        return CustomerDb(
            id = this.id.number,
            name = this.name,
            email = this.email,
            address = this.address,
            password = this.password
        )
    }

    fun List<Customer>.mapToDb(): List<CustomerDb> {
        val customerDbList = ArrayList<CustomerDb>()
        forEach { customer ->
            customerDbList.add(customer.mapToDb())
        }
        return customerDbList
    }

    fun CustomerDb.mapToDomain(): Customer {
        return Customer(
            id = Identity(this.id!!),
            name = this.name,
            email = this.email,
            address = this.address,
            password = this.password
        )
    }

    fun List<CustomerDb>.mapToDomain(): List<Customer> {
        val customerList = ArrayList<Customer>()
        forEach { customerDB ->
            customerList.add(customerDB.mapToDomain())
        }
        return customerList
    }
}