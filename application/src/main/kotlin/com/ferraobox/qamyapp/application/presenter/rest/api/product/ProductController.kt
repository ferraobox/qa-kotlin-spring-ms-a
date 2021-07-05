package com.ferraobox.qamyapp.application.presenter.rest.api.product

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.usecases.UseCaseExecutor
import com.ferraobox.qamyapp.application.core.usecases.product.GetAllProductsUseCase
import com.ferraobox.qamyapp.application.core.usecases.product.GetProductUseCase
import com.ferraobox.qamyapp.application.core.usecases.product.SearchProductsByNameOrDescriptionUseCase
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.ProductDomainDtoMapper.mapToDto
import com.ferraobox.qamyapp.dto.ProductResponse
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import java.util.concurrent.CompletableFuture


@Component
open class ProductController(
    private val useCaseExecutor: UseCaseExecutor,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getProductUseCase: GetProductUseCase,
    private val searchProductsByNameOrDescriptionUseCase: SearchProductsByNameOrDescriptionUseCase,
) : ProductResource {

    override fun allProducts(): CompletableFuture<List<Any?>?> {
        return useCaseExecutor.execute(
            getAllProductsUseCase,
            GetAllProductsUseCase.InputValues()
        ) { outputValues -> outputValues.products.mapToDto() }
    }

    override fun getByIdentity(@PathVariable id: Long): CompletableFuture<ProductResponse?> {
        return useCaseExecutor.execute(
            getProductUseCase,
            GetProductUseCase.InputValues(id = Identity(id))
        ) { outputValues -> outputValues.product!!.mapToDto() }
    }

    override fun getByMatchingName(@PathVariable text: String): CompletableFuture<List<ProductResponse?>?> {
        return useCaseExecutor.execute(
            searchProductsByNameOrDescriptionUseCase,
            SearchProductsByNameOrDescriptionUseCase.InputValues(searchText = text)
        ) { outputValues -> outputValues.products.mapToDto() }
    }
}
