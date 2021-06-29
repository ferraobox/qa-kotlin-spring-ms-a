package com.ferraobox.qamyapp.application.core.usecases.order

import com.ferraobox.qamyapp.application.core.domain.Order
import com.ferraobox.qamyapp.application.core.repositories.IOrderRepository
import org.springframework.stereotype.Component

@Component
open class DeleteOrderUseCase(repository: IOrderRepository) : UpdateOrderUseCase(repository) {
   override fun updateStatus(order: Order): Order {
        return order.delete()
    }
}