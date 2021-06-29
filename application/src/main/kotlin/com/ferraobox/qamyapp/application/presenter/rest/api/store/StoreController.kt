package com.ferraobox.qamyapp.application.presenter.rest.api.store

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.usecases.UseCaseExecutor
import com.ferraobox.qamyapp.application.core.usecases.store.GetAllStoresUseCase
import com.ferraobox.qamyapp.application.core.usecases.store.GetProductsByStoreUseCase
import com.ferraobox.qamyapp.application.core.usecases.store.GetStoreUseCase
import com.ferraobox.qamyapp.application.core.usecases.store.SearchStoresByNameUseCase
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.ProductDomainDtoMapper
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.StoreDomainDtoMapper
import com.ferraobox.qamyapp.dto.ProductResponse
import com.ferraobox.qamyapp.dto.StoreResponse
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import java.util.concurrent.CompletableFuture


@Component
class StoreController(
    private val useCaseExecutor: UseCaseExecutor,
    private val getAllStoresUseCase: GetAllStoresUseCase,
    private val searchStoresByNameUseCase: SearchStoresByNameUseCase,
    private val getStoreUseCase: GetStoreUseCase,
    private val getProductsByStoreUseCase: GetProductsByStoreUseCase,
    private val storeDomainDtoMapper: StoreDomainDtoMapper,
    private val productDomainDtoMapper: ProductDomainDtoMapper
) : StoreResource {

    override fun all(): CompletableFuture<List<Any?>?> {
        return useCaseExecutor.execute(
            getAllStoresUseCase,
            GetAllStoresUseCase.InputValues()
        ) { outputValues -> storeDomainDtoMapper.mapToDto(outputValues.stores) }
    }

    override fun getAllStoresByNameMatching(@PathVariable text: String?): CompletableFuture<List<StoreResponse?>?>? {
        return useCaseExecutor.execute(
            searchStoresByNameUseCase,
            SearchStoresByNameUseCase.InputValues(searchText=text)
        ) { outputValues -> storeDomainDtoMapper.mapToDto(outputValues.stores) }
    }

    override fun getStoreByIdentity(@PathVariable id: Long): CompletableFuture<StoreResponse?> {
        return useCaseExecutor.execute(
            getStoreUseCase,
            GetStoreUseCase.InputValues(id=Identity(id))
        ) { outputValues -> storeDomainDtoMapper.mapToDto(outputValues.store) }
    }

    override fun getProductsBy(@PathVariable id: Long): CompletableFuture<List<ProductResponse?>?> {
        return useCaseExecutor.execute(
            getProductsByStoreUseCase,
            GetProductsByStoreUseCase.InputValues(id=Identity(id))
        ) { outputValues -> productDomainDtoMapper.mapToDto(outputValues.products) }
    }
}
