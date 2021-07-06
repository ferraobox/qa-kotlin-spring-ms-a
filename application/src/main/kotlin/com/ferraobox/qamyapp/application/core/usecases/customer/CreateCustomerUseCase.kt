package com.ferraobox.qamyapp.application.core.usecases.customer

import com.ferraobox.qamyapp.application.core.domain.Customer
import com.ferraobox.qamyapp.application.core.domain.EmailAlreadyUsedException
import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.repositories.ICustomerRepository
import com.ferraobox.qamyapp.application.core.usecases.UseCase
import org.springframework.stereotype.Component


@Component
open class CreateCustomerUseCase(
    private val repository: ICustomerRepository
): UseCase<CreateCustomerUseCase.InputValues, CreateCustomerUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        if (repository.existsByEmail(input.email)) {
            throw EmailAlreadyUsedException("Email address already in use!")
        }
        val customer = Customer(
            id= Identity(),
            name=input.name,
            email=input.email,
            address=input.address,
            password=input.password
        )
        return OutputValues(repository.persist(customer))
    }

    data class InputValues (
        var name: String,
        var email: String,
        var address: String,
        var password: String
    ): UseCase.InputValues

    data class OutputValues (
        var customer: Customer
    ): UseCase.OutputValues
}
