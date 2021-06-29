package com.ferraobox.qamyapp.application.presenter.mappers.domainDto

import com.ferraobox.qamyapp.application.core.domain.Customer
import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.database.entities.IdConverter
import com.ferraobox.qamyapp.dto.CustomerResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.springframework.stereotype.Component

@Component
@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring",
    imports = [IdConverter::class, Identity::class],
    uses = []
)
interface CustomerDomainDtoMapper : BaseDomainDtoMapper<Customer?, CustomerResponse?> {
    @Mappings(
        Mapping(expression = "java(customer.getId().getNumber())", target = "id"),
        Mapping(source = "name", target = "name"),
        Mapping(source = "email", target = "email"),
        Mapping(source = "address", target = "address")
    )
    override fun mapToDto(customer: Customer?): CustomerResponse?
    override fun mapToDto(customer: List<Customer?>?): List<CustomerResponse?>?

    @Mappings(
        Mapping(expression = "java(new Identity(customer.getId()))", target = "id"),
        Mapping(source = "name", target = "name"),
        Mapping(source = "email", target = "email")
    )
    override fun mapToDomain(customer: CustomerResponse?): Customer?
    override fun mapToDomain(customer: List<CustomerResponse?>?): List<Customer?>?
}
