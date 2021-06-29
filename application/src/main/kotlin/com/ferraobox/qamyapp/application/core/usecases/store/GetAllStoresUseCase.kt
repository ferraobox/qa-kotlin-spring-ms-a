package com.ferraobox.qamyapp.application.core.usecases.store

import com.ferraobox.qamyapp.application.core.domain.Store
import com.ferraobox.qamyapp.application.core.repositories.IStoreRepository
import com.ferraobox.qamyapp.application.core.usecases.UseCase
import org.springframework.stereotype.Component


@Component
class GetAllStoresUseCase(private val repository: IStoreRepository) :
    UseCase<GetAllStoresUseCase.InputValues, GetAllStoresUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        return OutputValues(repository.all())
    }

    class InputValues : UseCase.InputValues

    data class OutputValues (
        var stores: List<Store>?
    ): UseCase.OutputValues
}