package com.ferraobox.qamyapp.application.presenter.mappers.domainDto

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Product
import com.ferraobox.qamyapp.application.database.entities.IdConverter
import com.ferraobox.qamyapp.dto.ProductResponse
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
interface ProductDomainDtoMapper : BaseDomainDtoMapper<Product?, ProductResponse?> {
    @Mappings(
        Mapping(expression = "java(IdConverter.convertId(product.getId()))", target = "id"),
        Mapping(source = "name", target = "name"),
        Mapping(source = "description", target = "description"),
        Mapping(source = "price", target = "price"),
        Mapping(expression = "java(IdConverter.convertId(product.getStore().getId()))", target = "storeId")
    )
    override fun mapToDto(product: Product?): ProductResponse?
    override fun mapToDto(product: List<Product?>?): List<ProductResponse?>?

    @Mappings(
        Mapping(expression = "java(new Identity(product.getId()))", target = "id")
    )
    override fun mapToDomain(product: ProductResponse?): Product?
    override fun mapToDomain(product: List<ProductResponse?>?): List<Product?>?
}