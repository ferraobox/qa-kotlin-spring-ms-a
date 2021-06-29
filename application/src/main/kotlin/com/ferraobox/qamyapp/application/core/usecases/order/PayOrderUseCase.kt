package com.ferraobox.qamyapp.application.core.usecases.order

import com.ferraobox.qamyapp.application.core.domain.Order
import com.ferraobox.qamyapp.application.core.repositories.IOrderRepository
import org.springframework.stereotype.Component

@Component
open class PayOrderUseCase(repository: IOrderRepository) : UpdateOrderUseCase(repository) {
    override fun updateStatus(order: Order): Order {
        return repository.persist(order.pay())
    }
}