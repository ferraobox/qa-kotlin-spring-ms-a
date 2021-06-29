package com.ferraobox.qamyapp.application.presenter.rest.api.cousine

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.usecases.UseCaseExecutor
import com.ferraobox.qamyapp.application.core.usecases.cousine.GetAllCousinesUseCase
import com.ferraobox.qamyapp.application.core.usecases.cousine.GetStoresByCousineUseCase
import com.ferraobox.qamyapp.application.core.usecases.cousine.SearchCousineByNameUseCase
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.CousineDomainDtoMapper
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.StoreDomainDtoMapper
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
    private val cousineDomainDtoMapper: CousineDomainDtoMapper,
    private val storeDomainDtoMapper: StoreDomainDtoMapper
) : CousineResource {

    override fun getStoresByCousineId(@PathVariable id: Long): CompletableFuture<List<StoreResponse?>?> {
        return useCaseExecutor.execute(
            getStoresByCousineUseCase,
            GetStoresByCousineUseCase.InputValues(id=Identity(id))
        ) { (outputValues) -> storeDomainDtoMapper.mapToDto(outputValues) }
    }

   override fun allCousines(): CompletableFuture<List<Any?>?> {
        return useCaseExecutor.execute(
            getAllCousinesUseCase,
            GetAllCousinesUseCase.InputValues()
        ) { outputValues -> cousineDomainDtoMapper.mapToDto(outputValues.cousines) }
   }

    override fun getAllCousinesByNameMatching(@PathVariable text: String?): CompletableFuture<List<CousineResponse?>?>? {
        return useCaseExecutor.execute(
            searchCousineByNameUseCase,
            SearchCousineByNameUseCase.InputValues(searchText=text!!)
        ) { outputValues -> cousineDomainDtoMapper.mapToDto(outputValues.cousines) }
    }
}
