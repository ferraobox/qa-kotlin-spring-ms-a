package com.ferraobox.qamyapp.application.core.repositories

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Order
import java.util.*

interface IOrderRepository {
    fun persist(order: Order): Order
    fun getById(id: Identity?): Optional<Order>
}