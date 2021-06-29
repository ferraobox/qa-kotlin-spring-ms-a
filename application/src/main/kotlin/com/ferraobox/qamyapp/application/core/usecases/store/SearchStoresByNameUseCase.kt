package com.ferraobox.qamyapp.application.core.usecases.store

import com.ferraobox.qamyapp.application.core.domain.Store
import com.ferraobox.qamyapp.application.core.repositories.IStoreRepository
import com.ferraobox.qamyapp.application.core.usecases.UseCase
import org.springframework.stereotype.Component


@Component
class SearchStoresByNameUseCase(private val repository: IStoreRepository) :
    UseCase<SearchStoresByNameUseCase.InputValues, SearchStoresByNameUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        return OutputValues(repository.searchByName(input.searchText))
    }

    data class InputValues(
        var searchText: String?
    ): UseCase.InputValues

    data class OutputValues (
        var stores: List<Store>?
    ): UseCase.OutputValues
}