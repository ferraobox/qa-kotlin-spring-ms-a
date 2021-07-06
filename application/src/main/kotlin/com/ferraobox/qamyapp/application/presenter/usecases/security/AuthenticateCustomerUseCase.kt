package com.ferraobox.qamyapp.application.presenter.usecases.security

import com.ferraobox.qamyapp.application.core.usecases.UseCase
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
open class AuthenticateCustomerUseCase(
    private val authenticationManager: AuthenticationManager,
    private val jwtProvider: JwtProvider
) : UseCase<AuthenticateCustomerUseCase.InputValues, AuthenticateCustomerUseCase.OutputValues> {

   override fun execute(input: InputValues): OutputValues {
        val authentication = authenticationManager.authenticate(input.authenticationToken)
        return OutputValues(jwtProvider.generateToken(authentication))
    }

    data class InputValues(
        var authenticationToken: UsernamePasswordAuthenticationToken
    ) : UseCase.InputValues

    data class OutputValues(
        var jwtToken: String
    ) : UseCase.OutputValues
}
