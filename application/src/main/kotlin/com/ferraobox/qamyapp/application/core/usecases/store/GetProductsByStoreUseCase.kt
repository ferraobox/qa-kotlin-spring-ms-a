package com.ferraobox.qamyapp.application.core.usecases.store

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.NotFoundException
import com.ferraobox.qamyapp.application.core.domain.Product
import com.ferraobox.qamyapp.application.core.repositories.IStoreRepository
import com.ferraobox.qamyapp.application.core.usecases.UseCase
import org.springframework.stereotype.Component


@Component
class GetProductsByStoreUseCase(private val repository: IStoreRepository) :
    UseCase<GetProductsByStoreUseCase.InputValues, GetProductsByStoreUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val id: Identity = input.id
        val products: List<Product> = repository.getProductsById(id)
        if (products.isEmpty()) {
            throw NotFoundException("No store found by identity: " + id.number)
        }
        return OutputValues(products)
    }

    data class InputValues (
        var id: Identity
    ): UseCase.InputValues

    data class OutputValues (
        var products: List<Product>
    ): UseCase.OutputValues
}