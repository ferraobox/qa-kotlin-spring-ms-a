package com.ferraobox.qamyapp.application.presenter.mappers.inputOutputDto

import com.ferraobox.qamyapp.application.core.domain.Customer
import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.OrderItem
import com.ferraobox.qamyapp.application.core.usecases.order.CreateOrderUseCase
import com.ferraobox.qamyapp.application.presenter.usecases.security.UserPrincipal
import com.ferraobox.qamyapp.dto.OrderRequest
import com.ferraobox.qamyapp.dto.OrderRequestItem
import org.springframework.stereotype.Component

@Component
object CreateOrderInputMapper {

    fun map(orderRequest: OrderRequest, userPrincipal: UserPrincipal): CreateOrderUseCase.InputValues {
        return CreateOrderUseCase.InputValues(
            customer = Customer(
                id = Identity(userPrincipal.id!!),
                name = userPrincipal.name!!,
                email = userPrincipal.email!!,
                address = userPrincipal.address!!,
                password = userPrincipal.password
            ),
            storeId = Identity(orderRequest.storeId), orderItems = orderRequest.orderItems)
    }

    fun OrderRequestItem.map(): CreateOrderUseCase.InputItem {
        return CreateOrderUseCase.InputItem(id = Identity(this.id), quantity = this.quantity)
    }

    fun List<OrderRequestItem>.map(): List<CreateOrderUseCase.InputItem> {
        val orderRequestItemList = ArrayList<CreateOrderUseCase.InputItem>()
        forEach { orderRequestItem -> orderRequestItemList.add(orderRequestItem.map()) }
        return orderRequestItemList
    }
}