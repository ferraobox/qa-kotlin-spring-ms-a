package com.ferraobox.qamyapp.application.presenter.mappers.inputOutputDto

import com.ferraobox.qamyapp.application.presenter.usecases.security.AuthenticateCustomerUseCase
import com.ferraobox.qamyapp.dto.SignInRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component

@Component
@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring",
    imports = [UsernamePasswordAuthenticationToken::class],
    uses = []
)
interface AuthenticateCustomerUseCaseInputMapper {
    @Mapping(
        target = "authenticationToken",
        expression = "java(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()))"
    )
    fun map(signInRequest: SignInRequest?): AuthenticateCustomerUseCase.InputValues
}
