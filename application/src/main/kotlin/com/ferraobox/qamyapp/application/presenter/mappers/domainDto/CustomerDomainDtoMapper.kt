package com.ferraobox.qamyapp.application.presenter.mappers.domainDto

import com.ferraobox.qamyapp.application.core.domain.Customer
import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.dto.CustomerResponse
import org.springframework.stereotype.Component

@Component
object CustomerDomainDtoMapper {

    fun Customer.mapToDto(): CustomerResponse {
        return CustomerResponse(
            id = this.id.number,
            name = this.name,
            email = this.email, address = this.address
        )
    }

    fun List<Customer>.mapToDto(): List<CustomerResponse> {
        val customerListResponse = ArrayList<CustomerResponse>()
        forEach { customer -> customerListResponse.add(customer.mapToDto()) }
        return customerListResponse
    }

    fun CustomerResponse.mapToDomain(): Customer {
        return Customer(
            id = Identity(this.id),
            name = this.name, email = this.email, address = this.address, password = ""
        )
    }

    fun List<CustomerResponse>.mapToDomain(): List<Customer> {
        val customerList = ArrayList<Customer>()
        forEach { customer -> customerList.add(customer.mapToDomain()) }
        return customerList
    }

}
