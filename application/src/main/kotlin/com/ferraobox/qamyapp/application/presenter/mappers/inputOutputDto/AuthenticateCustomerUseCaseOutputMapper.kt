package com.ferraobox.qamyapp.application.presenter.mappers.inputOutputDto

import com.ferraobox.qamyapp.application.presenter.usecases.security.AuthenticateCustomerUseCase
import com.ferraobox.qamyapp.dto.AuthenticationResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
object AuthenticateCustomerUseCaseOutputMapper {
    fun AuthenticateCustomerUseCase.OutputValues.map(): AuthenticationResponse {
        return AuthenticationResponse(success = true, this.jwtToken)
    }
}