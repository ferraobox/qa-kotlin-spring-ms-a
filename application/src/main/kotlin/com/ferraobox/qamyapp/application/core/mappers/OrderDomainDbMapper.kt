package com.ferraobox.qamyapp.application.core.mappers

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Order
import com.ferraobox.qamyapp.application.core.mappers.CustomerDomainDbMapper.mapToDb
import com.ferraobox.qamyapp.application.core.mappers.CustomerDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.core.mappers.OrderItemDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.core.mappers.StoreDomainDbMapper.mapToDb
import com.ferraobox.qamyapp.application.core.mappers.StoreDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.database.entities.OrderDb
import kotlin.collections.ArrayList


object OrderDomainDbMapper {

    fun Order.mapToDb(): OrderDb {
        return OrderDb(
            id = this.id.number,
            customer = this.customer.mapToDb(),
            store = this.store.mapToDb(),
            total = this.total,
            status = this.status,
            createdAt = this.createdAt,
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
            total = this.total,
            status = this.status,
            orderItems= this.orderItems.mapToDomain(),
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
