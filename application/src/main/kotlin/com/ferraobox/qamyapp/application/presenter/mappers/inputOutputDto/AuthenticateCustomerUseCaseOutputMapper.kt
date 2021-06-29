package com.ferraobox.qamyapp.application.presenter.mappers.inputOutputDto

import com.ferraobox.qamyapp.application.presenter.usecases.security.AuthenticateCustomerUseCase
import com.ferraobox.qamyapp.dto.AuthenticationResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring",
    imports = [ResponseEntity::class],
    uses = []
)
interface AuthenticateCustomerUseCaseOutputMapper {
    @Mapping(target = "token", source = "jwtToken")
    fun map(outputValues: AuthenticateCustomerUseCase.OutputValues?): AuthenticationResponse?
}