package com.ferraobox.qamyapp.application.core.usecases

import java.util.concurrent.CompletableFuture
import java.util.function.Function

interface UseCaseExecutor {
    fun <RX, I : UseCase.InputValues, O : UseCase.OutputValues> execute(
        useCase: UseCase<I, O>,
        input: I,
        outputMapper: Function<O, RX>
    ): CompletableFuture<RX>
}