package com.ferraobox.qamyapp.application.core.mappers

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Order
import com.ferraobox.qamyapp.application.database.entities.IdConverter
import com.ferraobox.qamyapp.application.database.entities.OrderDb
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
    uses = [OrderItemDomainDbMapper::class, CustomerDomainDbMapper::class, StoreDomainDbMapper::class]
)
interface OrderDomainDbMapper : BaseDomainDbMapper<Order?, OrderDb?> {
    @Mappings(
        Mapping(expression = "java(IdConverter.INSTANCE.convertId(order.getId()))", target = "id"),
        Mapping(source = "customer", target = "customer"),
        Mapping(source = "store", target = "store"),
        Mapping(source = "total", target = "total"),
        Mapping(source = "status", target = "status"),
        Mapping(source = "createdAt", target = "createdAt"),
        Mapping(source = "updatedAt", target = "updatedAt")
    )
    override fun mapToDb(order: Order?): OrderDb?
    override fun mapToDb(order: List<Order?>?): List<OrderDb?>?

    @Mappings(
        Mapping(expression = "java(new Identity(order.getId()))", target = "id"),
        Mapping(source = "customer", target = "customer"),
        Mapping(source = "store", target = "store"),
        Mapping(source = "total", target = "total"),
        Mapping(source = "status", target = "status"),
        Mapping(source = "createdAt", target = "createdAt"),
        Mapping(source = "updatedAt", target = "updatedAt")
    )
    override fun mapToDomain(order: OrderDb?): Order?
    override fun mapToDomain(order: List<OrderDb?>?): List<Order?>?
}
