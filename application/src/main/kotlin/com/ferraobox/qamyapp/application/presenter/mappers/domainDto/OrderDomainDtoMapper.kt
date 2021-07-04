package com.ferraobox.qamyapp.application.presenter.mappers.domainDto

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Order
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.CustomerDomainDtoMapper.mapToDomain
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.CustomerDomainDtoMapper.mapToDto
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.OrderItemDomainDtoMapper.mapToDomain
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.OrderItemDomainDtoMapper.mapToDto
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.StoreDomainDtoMapper.mapToDomain
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.StoreDomainDtoMapper.mapToDto
import com.ferraobox.qamyapp.dto.OrderResponse
import com.ferraobox.qamyapp.dto.Status
import org.springframework.stereotype.Component

@Component
object OrderDomainDtoMapper {

    fun Order.mapToDto(): OrderResponse {
        return OrderResponse(
            id = this.id.number,
            date = this.createdAt,
            customer = this.customer.mapToDto(),
            store = this.store.mapToDto(),
            contact = this.customer.name,
            total = this.total,
            status = Status.valueOf(this.status.toString()),
            lastUpdate = this.updatedAt,
            orderItems = this.orderItems.mapToDto()
        )
    }

    fun List<Order>.mapToDto(): List<OrderResponse> {
        val orderListResponse = ArrayList<OrderResponse>()
        forEach { order -> orderListResponse.add(order.mapToDto()) }
        return orderListResponse
    }

    fun OrderResponse.mapToDomain(): Order {
        return Order(
            id = Identity(this.id),
            status = com.ferraobox.qamyapp.application.core.domain.Status.valueOf(this.status.toString()),
            customer = this.customer.mapToDomain(),
            store = this.store.mapToDomain(),
            orderItems = this.orderItems.mapToDomain(),
            total = this.total,
            createdAt = this.date,
            updatedAt = this.lastUpdate
        )
    }

    fun List<OrderResponse>.mapToDomain(): List<Order> {
        val orderList = ArrayList<Order>()
        forEach { order -> orderList.add(order.mapToDomain()) }
        return orderList
    }

}