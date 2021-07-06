package com.ferraobox.qamyapp.application.core.mappers

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.OrderItem
import com.ferraobox.qamyapp.application.core.mappers.OrderDomainDbMapper.mapToDb
import com.ferraobox.qamyapp.application.core.mappers.OrderDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.core.mappers.ProductDomainDbMapper.mapToDb
import com.ferraobox.qamyapp.application.core.mappers.ProductDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.database.entities.IdConverter
import com.ferraobox.qamyapp.application.database.entities.OrderItemDb
import java.util.*
import kotlin.collections.ArrayList


object OrderItemDomainDbMapper {

    fun OrderItem.mapToDb(): OrderItemDb {
        return OrderItemDb(
            id = IdConverter.convertId(this.id),
            order = null,
            quantity = this.quantity,
            product = this.product.mapToDb(),
            total = this.total,
            price = this.price
        )
    }

    fun List<OrderItem>.mapToDb(): MutableSet<OrderItemDb> {
        val orderItemDbList = HashSet<OrderItemDb>()
        forEach { orderItem -> orderItemDbList.add(orderItem.mapToDb()) }
        return orderItemDbList
    }


    fun OrderItemDb.mapToDomain(): OrderItem {
        return OrderItem(
            id = Identity(this.id!!),
            quantity = this.quantity,
            product = this.product.mapToDomain(),
            total = this.total,
            price = this.price
        )
    }

    fun MutableSet<OrderItemDb>.mapToDomain(): List<OrderItem> {
        val orderItemList = ArrayList<OrderItem>()
        forEach { orderItemDb -> orderItemList.add(orderItemDb.mapToDomain()) }
        return orderItemList
    }

}