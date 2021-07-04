package com.ferraobox.qamyapp.application.presenter.mappers.inputOutputDto

import com.ferraobox.qamyapp.application.presenter.usecases.security.AuthenticateCustomerUseCase
import com.ferraobox.qamyapp.dto.SignInRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component

@Component
object AuthenticateCustomerUseCaseInputMapper {
    fun SignInRequest.map(): AuthenticateCustomerUseCase.InputValues {
        return AuthenticateCustomerUseCase.InputValues(UsernamePasswordAuthenticationToken(this.email,this.password))
    }
}
