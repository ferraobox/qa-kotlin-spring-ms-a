package com.ferraobox.qamyapp.application.core.usecases.order

import com.ferraobox.qamyapp.application.core.domain.Customer
import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Order
import com.ferraobox.qamyapp.application.core.usecases.UseCase
import org.springframework.stereotype.Component

@Component
open class GetCustomerOrderUseCase(private val getOrderUseCase: GetOrderUseCase) :
    UseCase<GetCustomerOrderUseCase.InputValues, GetCustomerOrderUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val order: Order? = getOrderUseCase
            .execute(input.let { GetOrderUseCase.InputValues(id= it.id) }).order
        return OutputValues(order!!.customer)
    }

    data class InputValues (
        var id: Identity
    ) : UseCase.InputValues

    data class OutputValues (
        var customer: Customer?
    ): UseCase.OutputValues


}