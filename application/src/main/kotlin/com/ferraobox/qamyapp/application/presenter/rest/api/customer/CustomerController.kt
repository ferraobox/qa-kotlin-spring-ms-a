package com.ferraobox.qamyapp.application.presenter.rest.api.customer

import com.ferraobox.qamyapp.application.core.usecases.UseCaseExecutor
import com.ferraobox.qamyapp.application.core.usecases.customer.CreateCustomerUseCase
import com.ferraobox.qamyapp.application.presenter.binding.PublisherService
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.CustomerDomainDtoMapper.mapToDto
import com.ferraobox.qamyapp.application.presenter.mappers.inputOutputDto.AuthenticateCustomerUseCaseInputMapper.map
import com.ferraobox.qamyapp.application.presenter.mappers.inputOutputDto.AuthenticateCustomerUseCaseOutputMapper.map
import com.ferraobox.qamyapp.application.presenter.mappers.inputOutputDto.CreateCustomerInputMapper.map
import com.ferraobox.qamyapp.application.presenter.usecases.security.AuthenticateCustomerUseCase
import com.ferraobox.qamyapp.dto.AuthenticationResponse
import com.ferraobox.qamyapp.dto.CustomerResponse
import com.ferraobox.qamyapp.dto.SignInRequest
import com.ferraobox.qamyapp.dto.SignUpRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.concurrent.CompletableFuture
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@Component
class CustomerController(
    private val useCaseExecutor: UseCaseExecutor,
    private val createCustomerUseCase: CreateCustomerUseCase,
    private val authenticateCustomerUseCase: AuthenticateCustomerUseCase,
    private val publishService: PublisherService
) {

    fun signUp(
        @RequestBody signUpRequest: @Valid SignUpRequest?,
        httpServletRequest: HttpServletRequest?
    ): CompletableFuture<ResponseEntity<CustomerResponse>> {
        val response: CompletableFuture<ResponseEntity<CustomerResponse>> = useCaseExecutor.execute(
            createCustomerUseCase,
            signUpRequest!!.map()
        ) { outputValues ->
            val location = ServletUriComponentsBuilder
                .fromContextPath(httpServletRequest!!)
                .path("/Customer/{id}")
                .buildAndExpand(outputValues.customer.id.number)
                .toUri()
            ResponseEntity.created(location).body(outputValues.customer.mapToDto())
        }
        publishService.send("created user event", "execute-producer-out-0")
        return response
    }

    fun signIn(@RequestBody signInRequest: @Valid SignInRequest?): CompletableFuture<ResponseEntity<AuthenticationResponse>> {
        return useCaseExecutor.execute(
            authenticateCustomerUseCase,
            signInRequest!!.map()
        ) { outputValues -> ResponseEntity.ok(outputValues.map()) }
    }
}