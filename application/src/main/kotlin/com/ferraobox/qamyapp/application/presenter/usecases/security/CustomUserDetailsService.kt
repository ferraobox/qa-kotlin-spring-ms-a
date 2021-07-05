package com.ferraobox.qamyapp.application.presenter.usecases.security

import com.ferraobox.qamyapp.application.core.mappers.CustomerDomainDbMapper.mapToDb
import com.ferraobox.qamyapp.application.core.repositories.ICustomerRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
open class CustomUserDetailsService(private val customerRepository: ICustomerRepository) : UserDetailsService {

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val customerData = customerRepository
            .findByEmail(email)
            .orElseThrow { UsernameNotFoundException(String.format("User %s not found", email)) }
        return UserPrincipal.from(customerData.mapToDb())
    }

    @Transactional
    open fun loadUserById(id: Long): UserDetails {
        val customer = customerRepository
            .findById(id)
            .orElseThrow { UsernameNotFoundException(String.format("User %s not found", id)) }
        return UserPrincipal.from(customer.mapToDb())
    }
}