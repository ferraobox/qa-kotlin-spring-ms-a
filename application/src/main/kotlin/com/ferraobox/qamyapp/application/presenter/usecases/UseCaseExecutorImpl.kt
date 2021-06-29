package com.ferraobox.qamyapp.application.presenter.usecases

import com.ferraobox.qamyapp.application.core.usecases.UseCase
import com.ferraobox.qamyapp.application.core.usecases.UseCaseExecutor
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture
import java.util.function.Function


@Service
class UseCaseExecutorImpl : UseCaseExecutor {

    override fun <RX, I : UseCase.InputValues, O : UseCase.OutputValues> execute(
        useCase: UseCase<I, O>,
        input: I,
        outputMapper: Function<O, RX>
    ): CompletableFuture<RX> {
        return CompletableFuture
            .supplyAsync { input }
            .thenApplyAsync<O>(useCase::execute)
            .thenApplyAsync<RX>(outputMapper)
    }
}