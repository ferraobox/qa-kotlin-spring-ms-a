package com.ferraobox.qamyapp.application.core.usecases.cousine

import com.ferraobox.qamyapp.application.core.domain.Cousine
import com.ferraobox.qamyapp.application.core.repositories.ICousineRepository
import com.ferraobox.qamyapp.application.core.usecases.UseCase
import org.springframework.stereotype.Component


@Component
open class GetAllCousinesUseCase(private val repository: ICousineRepository) :
    UseCase<GetAllCousinesUseCase.InputValues, GetAllCousinesUseCase.OutputValues> {

    override fun execute(input: InputValues): OutputValues {
        return OutputValues(repository.all())
    }

    class InputValues() : UseCase.InputValues

    data class OutputValues(
        var cousines: List<Cousine>?
    ) : UseCase.OutputValues
}