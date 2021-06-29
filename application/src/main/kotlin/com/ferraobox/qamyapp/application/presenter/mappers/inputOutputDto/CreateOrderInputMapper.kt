package com.ferraobox.qamyapp.application.presenter.mappers.inputOutputDto

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.usecases.order.CreateOrderUseCase
import com.ferraobox.qamyapp.application.presenter.usecases.security.UserPrincipal
import com.ferraobox.qamyapp.dto.OrderRequest
import com.ferraobox.qamyapp.dto.OrderRequestItem
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.springframework.stereotype.Component

@Component
@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring",
    imports = [Identity::class],
    uses = []
)
interface CreateOrderInputMapper {

    @Mappings(
        Mapping(target = "customer.id", expression = "java(new Identity(userPrincipal.getId()))"),
        Mapping(target = "customer.name", source = "userPrincipal.name"),
        Mapping(target = "customer.email", source = "userPrincipal.email"),
        Mapping(target = "customer.address", source = "userPrincipal.address"),
        Mapping(target = "customer.password", source = "userPrincipal.password"),
        Mapping(target = "storeId", expression = "java(new Identity(orderRequest.getStoreId()))"),
        Mapping(target = "orderItems", source = "orderRequest.orderItems")
    )
    fun map(orderRequest: OrderRequest?, userPrincipal: UserPrincipal?): CreateOrderUseCase.InputValues

    @Mappings(
        Mapping(target = "id", expression = "java(new Identity(orderRequestItem.getId()))"),
        Mapping(target = "quantity", source = "quantity")
    )
    fun map(orderRequestItem: OrderRequestItem?): CreateOrderUseCase.InputItem
    fun map(orderRequestItem: List<OrderRequestItem?>?): List<CreateOrderUseCase.InputItem?>
}