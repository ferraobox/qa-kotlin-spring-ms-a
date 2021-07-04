package com.ferraobox.qamyapp.application.core.mappers

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Order
import com.ferraobox.qamyapp.application.core.mappers.CustomerDomainDbMapper.mapToDb
import com.ferraobox.qamyapp.application.core.mappers.CustomerDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.core.mappers.OrderItemDomainDbMapper.mapToDb
import com.ferraobox.qamyapp.application.core.mappers.OrderItemDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.core.mappers.StoreDomainDbMapper.mapToDb
import com.ferraobox.qamyapp.application.core.mappers.StoreDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.database.entities.IdConverter
import com.ferraobox.qamyapp.application.database.entities.OrderDb
import kotlin.collections.ArrayList


object OrderDomainDbMapper {

    fun Order.mapToDb(): OrderDb {
        return OrderDb(
            id = IdConverter.convertId(this.id),
            customer = this.customer.mapToDb(),
            store = this.store.mapToDb(),
            orderItems = this.orderItems.mapToDb(),
            total = this.total,
            status = this.status,
            createdAt = this.updatedAt,
            updatedAt = this.updatedAt
        )
    }

    fun List<Order>.mapToDb(): List<OrderDb> {
        val orderDbList = ArrayList<OrderDb>()
        forEach { order ->
            orderDbList.add(order.mapToDb())
        }
        return orderDbList
    }

    fun OrderDb.mapToDomain(): Order {
        return Order(
            id = Identity(this.id!!),
            customer = this.customer.mapToDomain(),
            store = this.store.mapToDomain(),
            orderItems = this.orderItems.mapToDomain(),
            total = this.total,
            status = this.status,
            createdAt = this.updatedAt,
            updatedAt = this.updatedAt
        )
    }

    fun List<OrderDb>.mapToDomain(): List<Order> {
        val orderList = ArrayList<Order>()
        forEach { orderDb ->
            orderList.add(orderDb.mapToDomain())
        }
        return orderList
    }


}
