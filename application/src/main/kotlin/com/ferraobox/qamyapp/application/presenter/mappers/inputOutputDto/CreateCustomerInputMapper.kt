package com.ferraobox.qamyapp.application.presenter.mappers.inputOutputDto

import com.ferraobox.qamyapp.application.core.usecases.customer.CreateCustomerUseCase
import com.ferraobox.qamyapp.dto.SignUpRequest
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder


object CreateCustomerInputMapper {

    private val passwordEncoder: PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    fun SignUpRequest.map(): CreateCustomerUseCase.InputValues {
        return CreateCustomerUseCase.InputValues(
            password = passwordEncoder.encode(this.password),
            name = this.name,
            email = this.email,
            address = this.address
        )
    }
}
