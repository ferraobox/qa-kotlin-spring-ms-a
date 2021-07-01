package com.ferraobox.qamyapp.application.core.usecases.cousine

import com.ferraobox.qamyapp.application.core.domain.Cousine
import com.ferraobox.qamyapp.application.core.repositories.ICousineRepository
import com.ferraobox.qamyapp.application.core.usecases.UseCase
import org.springframework.stereotype.Component


@Component
open class SearchCousineByNameUseCase(private val repository: ICousineRepository) :
    UseCase<SearchCousineByNameUseCase.InputValues, SearchCousineByNameUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        return OutputValues(cousines = repository.searchByName(input.searchText))
    }

    data class InputValues(
        var searchText: String
    ): UseCase.InputValues


    data class OutputValues(
        var cousines: List<Cousine>
    ) : UseCase.OutputValues
}