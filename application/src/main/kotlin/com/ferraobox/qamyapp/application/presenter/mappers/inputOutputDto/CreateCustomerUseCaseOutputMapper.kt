package com.ferraobox.qamyapp.application.presenter.mappers.inputOutputDto

import com.ferraobox.qamyapp.application.core.domain.Customer
import com.ferraobox.qamyapp.dto.ApiResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
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
interface CreateCustomerUseCaseOutputMapper {
    @Mappings(
        Mapping(target = "success", expression = "java(true)"),
        Mapping(target = "message", constant = "registered successfully")
    )
    fun map(customer: Customer?): ApiResponse
}