package com.ferraobox.qamyapp.application.presenter.usecases.security

import com.ferraobox.qamyapp.application.database.entities.CustomerDb
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserPrincipal(
    var id: Long?,
    var name: String?,
    var email: String?,
    private val password: String?,
    var address: String?,
    private val authorities: Collection<GrantedAuthority?>?
): UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority?>{
        return authorities!!
    }

    override fun getPassword(): String {
        return password!!
    }

    override fun getUsername(): String {
        return name!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    companion object {
        fun from(customer: CustomerDb): UserPrincipal {
            return UserPrincipal(
                customer.id,
                customer.name,
                customer.email,
                customer.password,
                customer.address,
                listOf(SimpleGrantedAuthority("ROLE_USER"))
            )
        }
    }
}
