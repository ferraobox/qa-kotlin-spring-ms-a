package com.ferraobox.qamyapp.application.core.mappers

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.OrderItem
import com.ferraobox.qamyapp.application.database.entities.IdConverter
import com.ferraobox.qamyapp.application.database.entities.OrderItemDb
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import java.util.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring",
    imports = [Arrays::class, HashSet::class, IdConverter::class, Identity::class],
    uses = [ProductDomainDbMapper::class]
)
interface OrderItemDomainDbMapper : BaseDomainDbMapper<OrderItem?, OrderItemDb?> {

    @Mappings(
        Mapping(expression = "java(IdConverter.INSTANCE.convertId(orderItem.getId()))", target = "id"),
        Mapping(source = "quantity", target = "quantity"),
        Mapping(source = "product", target = "product"),
        Mapping(source = "product.price", target = "price"),
        Mapping(source = "total", target = "total")
    )
    override fun mapToDb(orderItem: OrderItem?): OrderItemDb?
    override fun mapToDb(orderItem: List<OrderItem?>?): List<OrderItemDb?>?

    @Mappings(
        Mapping(expression = "java(new Identity(orderItem.getId()))", target = "id"),
        Mapping(source = "quantity", target = "quantity"),
        Mapping(source = "product", target = "product"),
        Mapping(source = "total", target = "total")
    )
    override fun mapToDomain(orderItem: OrderItemDb?): OrderItem?
    override fun mapToDomain(orderItem: List<OrderItemDb?>?): List<OrderItem?>?
}