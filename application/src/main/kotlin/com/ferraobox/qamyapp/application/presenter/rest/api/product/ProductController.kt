package com.ferraobox.qamyapp.application.presenter.rest.api.product

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.usecases.UseCaseExecutor
import com.ferraobox.qamyapp.application.core.usecases.product.GetAllProductsUseCase
import com.ferraobox.qamyapp.application.core.usecases.product.GetProductUseCase
import com.ferraobox.qamyapp.application.core.usecases.product.SearchProductsByNameOrDescriptionUseCase
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.ProductDomainDtoMapper
import com.ferraobox.qamyapp.dto.ProductResponse
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import java.util.concurrent.CompletableFuture


@Component
class ProductController(
    private val useCaseExecutor: UseCaseExecutor,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getProductUseCase: GetProductUseCase,
    private val searchProductsByNameOrDescriptionUseCase: SearchProductsByNameOrDescriptionUseCase,
    private val productDomainDtoMapper: ProductDomainDtoMapper
) : ProductResource {

    override fun allProducts(): CompletableFuture<List<Any?>?> {
        return useCaseExecutor.execute(
            getAllProductsUseCase,
            GetAllProductsUseCase.InputValues()
        ) { outputValues -> productDomainDtoMapper.mapToDto(outputValues.products, list = true) }
    }

    override fun getByIdentity(@PathVariable id: Long): CompletableFuture<ProductResponse?> {
        return useCaseExecutor.execute(
            getProductUseCase,
            GetProductUseCase.InputValues(id=Identity(id))
        ) { outputValues -> productDomainDtoMapper.mapToDto(outputValues.product) }
    }

    override fun getByMatchingName(@PathVariable text: String): CompletableFuture<List<ProductResponse?>?> {
        return useCaseExecutor.execute(
            searchProductsByNameOrDescriptionUseCase,
            SearchProductsByNameOrDescriptionUseCase.InputValues(searchText=text)
        ) { outputValues -> productDomainDtoMapper.mapToDto(outputValues.products, list = true) }
    }
}
