package com.ferraobox.qamyapp.application.core.usecases

interface UseCase<I : UseCase.InputValues, O : UseCase.OutputValues> {
    fun execute(input: I): O
    interface InputValues
    interface OutputValues
}