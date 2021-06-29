package com.ferraobox.qamyapp.application.core.usecases.order

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.NotFoundException
import com.ferraobox.qamyapp.application.core.domain.Order
import com.ferraobox.qamyapp.application.core.repositories.IOrderRepository
import com.ferraobox.qamyapp.application.core.usecases.UseCase
import org.springframework.stereotype.Component

@Component
open class GetOrderUseCase(private val repository: IOrderRepository) :
    UseCase<GetOrderUseCase.InputValues, GetOrderUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val id: Identity = input.id
        return repository.getById(id)
            .map { order -> OutputValues(order) }
            .orElseThrow { NotFoundException("Order %s not found", id.number) }
    }

    data class InputValues(
        var id: Identity
    ) : UseCase.InputValues


    data class OutputValues(
        var order: Order?
    ) : UseCase.OutputValues
}