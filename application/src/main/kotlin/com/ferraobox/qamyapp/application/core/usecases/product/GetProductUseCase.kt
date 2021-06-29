package com.ferraobox.qamyapp.application.core.usecases.product

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.NotFoundException
import com.ferraobox.qamyapp.application.core.domain.Product
import com.ferraobox.qamyapp.application.core.repositories.IProductRepository
import com.ferraobox.qamyapp.application.core.usecases.UseCase
import org.springframework.stereotype.Component


@Component
class GetProductUseCase(private val repository: IProductRepository) :
    UseCase<GetProductUseCase.InputValues, GetProductUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val id: Identity = input!!.id
        return repository.getById(id)
            .map { product -> OutputValues(product)}
            .orElseThrow { NotFoundException("Product %s not found", id.number) }
    }

    data class InputValues (
        var id: Identity
    ): UseCase.InputValues

    data class OutputValues (
        var product: Product?
    ): UseCase.OutputValues
}