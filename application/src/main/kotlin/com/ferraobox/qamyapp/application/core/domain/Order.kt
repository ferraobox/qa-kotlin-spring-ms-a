package com.ferraobox.qamyapp.application.core.domain

import java.time.Instant

data class Order(
    override var id: Identity,
    var status: Status?,
    var customer: Customer?,
    var store: Store?,
    var orderItems: List<OrderItem>?,
    var total: Double?,
    var createdAt: Instant?,
    var updatedAt: Instant?

) : BaseDomainEntity() {

    fun calculateTotal(orderItems: List<OrderItem?>): Double {
        return orderItems.sumByDouble { it!!.total }
    }

    fun delete(): Order {
        check(!(status !== Status.OPEN)) { "Order should be open to be cancelled" }
        return newInstanceWith(Status.CANCELLED)
    }

    fun delivery(): Order {
        check(!(status !== Status.PAID)) { "Order should be paid to be delivered" }
        return newInstanceWith(Status.DELIVERED)
    }

    fun pay(): Order {
        check(!(status !== Status.OPEN)) { "Order should be open to be paid" }
        return newInstanceWith(Status.PAID)
    }

    private fun newInstanceWith(status: Status): Order {
        return Order(
            id = id,
            status = status,
            customer = customer,
            store = store,
            orderItems = orderItems,
            total = total,
            createdAt = createdAt,
            updatedAt = Instant.now()
        )
    }
}
