package com.ferraobox.qamyapp.application.presenter.usecases.security

import com.ferraobox.qamyapp.application.core.repositories.ICustomerRepository
import com.ferraobox.qamyapp.application.database.entities.CustomerDb
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
open class CustomUserDetailsService : UserDetailsService {

    private val customerRepository: ICustomerRepository
        get() {
            return customerRepository
        }

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val customerData = customerRepository
            .findByEmail(email)
            ?.orElseThrow { UsernameNotFoundException(String.format("User %s not found", email)) }
        return UserPrincipal.from(customerData!!)
    }

    @Transactional
    open fun loadUserById(id: Long?): UserDetails {
        val customer = customerRepository
            .findById(id)
            ?.orElseThrow { UsernameNotFoundException(String.format("User %s not found", id)) }
        return UserPrincipal.from(customer!!)
    }
}