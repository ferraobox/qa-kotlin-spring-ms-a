package com.ferraobox.qamyapp.application.core.usecases.store

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.NotFoundException
import com.ferraobox.qamyapp.application.core.domain.Store
import com.ferraobox.qamyapp.application.core.repositories.IStoreRepository
import com.ferraobox.qamyapp.application.core.usecases.UseCase
import org.springframework.stereotype.Component


@Component
class GetStoreUseCase(private val repository: IStoreRepository) :
    UseCase<GetStoreUseCase.InputValues, GetStoreUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val id: Identity = input.id
        return repository.getById(id)
            .map { store -> OutputValues(store) }
            .orElseThrow { NotFoundException("Store %s not found", id.number) }
    }

    data class InputValues(
        var id: Identity
    ) : UseCase.InputValues

    data class OutputValues(
        var store: Store?
    ) : UseCase.OutputValues

}
