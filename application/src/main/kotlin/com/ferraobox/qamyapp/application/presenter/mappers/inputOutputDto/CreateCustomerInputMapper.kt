package com.ferraobox.qamyapp.application.presenter.mappers.inputOutputDto

import com.ferraobox.qamyapp.application.core.usecases.customer.CreateCustomerUseCase
import com.ferraobox.qamyapp.dto.SignUpRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", imports = [], uses = [])
abstract class CreateCustomerInputMapper {
    @Autowired
    val passwordEncoder: PasswordEncoder? = null

    @Mappings(
        Mapping(target = "password", expression = "java(getPasswordEncoder().encode(signUpRequest.getPassword()))"),
        Mapping(target = "name", source = "signUpRequest.name"),
        Mapping(target = "email", source = "signUpRequest.email"),
        Mapping(target = "address", source = "signUpRequest.address")
    )
    abstract fun map(signUpRequest: SignUpRequest?): CreateCustomerUseCase.InputValues
}