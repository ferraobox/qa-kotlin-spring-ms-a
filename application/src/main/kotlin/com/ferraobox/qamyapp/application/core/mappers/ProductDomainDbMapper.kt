package com.ferraobox.qamyapp.application.core.mappers

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Product
import com.ferraobox.qamyapp.application.database.entities.IdConverter
import com.ferraobox.qamyapp.application.database.entities.ProductDb
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.springframework.stereotype.Component
import java.util.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring",
    imports = [Arrays::class, HashSet::class, IdConverter::class, Identity::class],
    uses = [StoreDomainDbMapper::class]
)
interface ProductDomainDbMapper : BaseDomainDbMapper<Product?, ProductDb?> {

    @Mappings(
        Mapping(expression = "java(IdConverter.INSTANCE.convertId(product.getId()))", target = "id"),
        Mapping(source = "name", target = "name"),
        Mapping(source = "description", target = "description"),
        Mapping(source = "price", target = "price"),
        Mapping(source = "store", target = "store")
    )
    override fun mapToDb(product: Product?): ProductDb?
    override fun mapToDb(product: List<Product?>?): List<ProductDb?>?

    @Mappings(
        Mapping(expression = "java(new Identity(product.getId()))", target = "id"),
        Mapping(source = "name", target = "name"),
        Mapping(source = "description", target = "description"),
        Mapping(source = "price", target = "price"),
        Mapping(source = "store", target = "store"),
    )
    override fun mapToDomain(product: ProductDb?): Product?
    override fun mapToDomain(product: List<ProductDb?>?): List<Product?>?
}