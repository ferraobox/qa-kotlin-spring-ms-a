package com.ferraobox.qamyapp.application.core.usecases.product

import com.ferraobox.qamyapp.application.core.domain.Product
import com.ferraobox.qamyapp.application.core.repositories.IProductRepository
import com.ferraobox.qamyapp.application.core.usecases.UseCase
import org.springframework.stereotype.Component

@Component
class SearchProductsByNameOrDescriptionUseCase(private val repository: IProductRepository) :
    UseCase<SearchProductsByNameOrDescriptionUseCase.InputValues, SearchProductsByNameOrDescriptionUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        return OutputValues(repository.searchByNameOrDescription(input.searchText))
    }

    class InputValues(
        var searchText: String
    ) : UseCase.InputValues

    data class OutputValues(
        var products: List<Product>
    ) : UseCase.OutputValues
}