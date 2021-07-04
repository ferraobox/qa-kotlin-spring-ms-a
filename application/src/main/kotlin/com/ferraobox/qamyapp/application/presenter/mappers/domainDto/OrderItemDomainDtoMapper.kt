package com.ferraobox.qamyapp.application.presenter.mappers.domainDto

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.OrderItem
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.ProductDomainDtoMapper.mapToDomain
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.ProductDomainDtoMapper.mapToDto
import com.ferraobox.qamyapp.dto.OrderItemResponse
import org.springframework.stereotype.Component

@Component
object OrderItemDomainDtoMapper {

    fun OrderItem.mapToDto(): OrderItemResponse {
        return OrderItemResponse(
            product = this.product.mapToDto(), price = this.price, quantity = this.quantity, total = this.total
        )
    }

    fun List<OrderItem>.mapToDto(): List<OrderItemResponse> {
        val orderItemListResponse = ArrayList<OrderItemResponse>()
        forEach { orderItem -> orderItemListResponse.add(orderItem.mapToDto()) }
        return orderItemListResponse
    }

    fun OrderItemResponse.mapToDomain(): OrderItem {
        return OrderItem(
            id = Identity(),
            quantity = this.quantity,
            product = this.product.mapToDomain(),
            price = this.price,
            total = this.total
        )
    }

    fun List<OrderItemResponse>.mapToDomain(): List<OrderItem> {
        val orderItemList = ArrayList<OrderItem>()
        forEach { orderItem -> orderItemList.add(orderItem.mapToDomain()) }
        return orderItemList
    }
}
