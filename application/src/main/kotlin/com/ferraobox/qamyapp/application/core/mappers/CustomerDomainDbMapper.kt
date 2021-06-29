package com.ferraobox.qamyapp.application.core.mappers

import com.ferraobox.qamyapp.application.core.domain.Customer
import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.database.entities.CustomerDb
import com.ferraobox.qamyapp.application.database.entities.IdConverter
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.springframework.stereotype.Component
import java.util.*

@Component
@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring",
    imports = [Arrays::class, IdConverter::class, Identity::class],
    uses = []
)
interface CustomerDomainDbMapper : BaseDomainDbMapper<Customer?, CustomerDb?> {
    @Mappings(
        Mapping(expression = "java(IdConverter.convertId(customer.getId()))", target = "id"),
        Mapping(source = "name", target = "name"),
        Mapping(source = "email", target = "email"),
        Mapping(source = "address", target = "address"),
        Mapping(source = "password", target = "password"),
    )
    override fun mapToDb(customer: Customer?): CustomerDb?
    override fun mapToDb(customer: List<Customer?>?): List<CustomerDb?>?

    @Mappings(
        Mapping(expression = "java(new Identity(customer.getId()))", target = "id"),
        Mapping(source = "name", target = "name"),
        Mapping(source = "email", target = "email"),
        Mapping(source = "address", target = "address"),
        Mapping(source = "password", target = "password"),
    )
    override fun mapToDomain(customer: CustomerDb?): Customer?
    override fun mapToDomain(customer: List<CustomerDb?>?): List<Customer?>?
}