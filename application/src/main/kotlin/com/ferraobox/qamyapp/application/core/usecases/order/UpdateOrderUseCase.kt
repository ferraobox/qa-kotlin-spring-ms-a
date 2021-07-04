package com.ferraobox.qamyapp.application.core.usecases.order

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.NotFoundException
import com.ferraobox.qamyapp.application.core.domain.Order
import com.ferraobox.qamyapp.application.core.repositories.IOrderRepository
import com.ferraobox.qamyapp.application.core.usecases.UseCase

abstract class UpdateOrderUseCase(protected val repository: IOrderRepository) :
    UseCase<UpdateOrderUseCase.InputValues, UpdateOrderUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val id: Identity = input.id
        return repository.getById(id).map { order -> updateStatus(order) }
            .map { order -> order?.let { persistAndReturn(it) } }
            .orElseThrow { NotFoundException("Order %s not found", id) }
    }

    protected abstract fun updateStatus(order: Order): Order

    private fun persistAndReturn(order: Order): OutputValues {
        return OutputValues(repository.persist(order))
    }

    data class InputValues(
        var id: Identity
    ) : UseCase.InputValues

    data class OutputValues(
        var order: Order?
    ) : UseCase.OutputValues
}