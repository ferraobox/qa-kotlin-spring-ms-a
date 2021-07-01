package com.ferraobox.qamyapp.application.presenter.mappers.domainDto

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.OrderItem
import com.ferraobox.qamyapp.application.database.entities.IdConverter
import com.ferraobox.qamyapp.dto.OrderItemResponse
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
interface OrderItemDomainDtoMapper : BaseDomainDtoMapper<OrderItem?, OrderItemResponse?> {
    @Mappings(
        Mapping(source = "product.name", target = "name"),
        Mapping(source = "product.price", target = "price"),
        Mapping(source = "quantity", target = "quantity"),
        Mapping(source = "total", target = "total")
    )
    override fun mapToDto(customer: OrderItem?): OrderItemResponse?
    override fun mapToDto(customer: List<OrderItem?>, list: Boolean): List<OrderItemResponse?>
    override fun mapToDomain(customer: OrderItemResponse?): OrderItem?
    override fun mapToDomain(customer: List<OrderItemResponse?>, list: Boolean): List<OrderItem?>
}
