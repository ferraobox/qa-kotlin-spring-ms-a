package com.ferraobox.qamyapp.application.core.usecases.product

import com.ferraobox.qamyapp.application.core.domain.Product
import com.ferraobox.qamyapp.application.core.repositories.IProductRepository
import com.ferraobox.qamyapp.application.core.usecases.UseCase
import org.springframework.stereotype.Component

@Component
class GetAllProductsUseCase(private val repository: IProductRepository) :
    UseCase<GetAllProductsUseCase.InputValues, GetAllProductsUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        return OutputValues(repository.all())
    }

    class InputValues : UseCase.InputValues

    data class OutputValues(
        var products: List<Product>
    ) : UseCase.OutputValues

}