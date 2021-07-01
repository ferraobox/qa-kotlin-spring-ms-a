package com.ferraobox.qamyapp.application.core.usecases.cousine

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.NotFoundException
import com.ferraobox.qamyapp.application.core.domain.Store
import com.ferraobox.qamyapp.application.core.repositories.IStoreRepository
import com.ferraobox.qamyapp.application.core.usecases.UseCase
import org.springframework.stereotype.Component


@Component
open class GetStoresByCousineUseCase(private val repository: IStoreRepository) :
    UseCase<GetStoresByCousineUseCase.InputValues, GetStoresByCousineUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        val id: Identity = input.id
        val stores: List<Store>? = repository.getStoresById(id)
        if (stores!!.isEmpty()) {
            throw NotFoundException("Cousine %s not found", id.number)
        }
        return OutputValues(stores)
    }

    data class InputValues(
        var id: Identity
    ) : UseCase.InputValues

    data class OutputValues(
        var stores: List<Store>
    ) : UseCase.OutputValues
}
