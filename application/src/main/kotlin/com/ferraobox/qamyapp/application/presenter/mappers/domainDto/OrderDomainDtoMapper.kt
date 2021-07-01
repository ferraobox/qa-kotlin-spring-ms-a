package com.ferraobox.qamyapp.application.presenter.mappers.domainDto

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Order
import com.ferraobox.qamyapp.application.database.entities.IdConverter
import com.ferraobox.qamyapp.dto.OrderResponse
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
    uses = [OrderItemDomainDtoMapper::class, CustomerDomainDtoMapper::class, StoreDomainDtoMapper::class]
)
interface OrderDomainDtoMapper : BaseDomainDtoMapper<Order?, OrderResponse?> {

    @Mappings(
        Mapping(expression = "java(order.getId().getNumber())", target = "id"),
        Mapping(source = "createdAt", target = "date"),
        Mapping(source = "updatedAt", target = "lastUpdate"),
        Mapping(source = "total", target = "total"),
        Mapping(source = "status", target = "status"),
        Mapping(expression = "java(order.getCustomer().getName())", target = "contact"),
        Mapping(source = "orderItems", target = "orderItems"),
        Mapping(source = "customer", target = "customer"),
        Mapping(source = "store", target = "store")
    )
    override fun mapToDto(order: Order?): OrderResponse?
    override fun mapToDto(order: List<Order?>, list: Boolean): List<OrderResponse?>

    @Mappings(
        Mapping(expression = "java(new Identity(order.getId()))", target = "id")
    )
    override fun mapToDomain(order: OrderResponse?): Order?
    override fun mapToDomain(order: List<OrderResponse?>, list: Boolean): List<Order?>
}