package com.ferraobox.qamyapp.application.presenter.rest.api.cousine

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.usecases.UseCaseExecutor
import com.ferraobox.qamyapp.application.core.usecases.cousine.GetAllCousinesUseCase
import com.ferraobox.qamyapp.application.core.usecases.cousine.GetStoresByCousineUseCase
import com.ferraobox.qamyapp.application.core.usecases.cousine.SearchCousineByNameUseCase
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.CousineDomainDtoMapper.mapToDto
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.StoreDomainDtoMapper.mapToDto
import com.ferraobox.qamyapp.dto.CousineResponse
import com.ferraobox.qamyapp.dto.StoreResponse
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import java.util.concurrent.CompletableFuture


@Component
class CousineController(
    private val useCaseExecutor: UseCaseExecutor,
    private val getAllCousinesUseCase: GetAllCousinesUseCase,
    private val getStoresByCousineUseCase: GetStoresByCousineUseCase,
    private val searchCousineByNameUseCase: SearchCousineByNameUseCase,
) : CousineResource {

    override fun getStoresByCousineId(@PathVariable id: Long): CompletableFuture<List<StoreResponse?>?> {
        return useCaseExecutor.execute(
            getStoresByCousineUseCase,
            GetStoresByCousineUseCase.InputValues(id = Identity(id))
        ) { (outputValues) -> outputValues.mapToDto() }
    }

    override fun allCousines(): CompletableFuture<List<Any?>?> {
        return useCaseExecutor.execute(
            getAllCousinesUseCase,
            GetAllCousinesUseCase.InputValues()
        ) { outputValues -> outputValues.cousines.mapToDto() }
    }

    override fun getAllCousinesByNameMatching(@PathVariable text: String): CompletableFuture<List<CousineResponse?>?> {
        return useCaseExecutor.execute(
            searchCousineByNameUseCase,
            SearchCousineByNameUseCase.InputValues(searchText = text)
        ) { outputValues -> outputValues.cousines.mapToDto() }
    }
}
