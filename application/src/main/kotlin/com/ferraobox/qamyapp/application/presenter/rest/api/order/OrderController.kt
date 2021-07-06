package com.ferraobox.qamyapp.application.presenter.rest.api.order

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.usecases.UseCaseExecutor
import com.ferraobox.qamyapp.application.core.usecases.order.*
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.CustomerDomainDtoMapper.mapToDto
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.OrderDomainDtoMapper.mapToDto
import com.ferraobox.qamyapp.application.presenter.mappers.inputOutputDto.CreateOrderInputMapper
import com.ferraobox.qamyapp.application.presenter.usecases.security.CurrentUser
import com.ferraobox.qamyapp.application.presenter.usecases.security.UserPrincipal
import com.ferraobox.qamyapp.dto.ApiResponse
import com.ferraobox.qamyapp.dto.CustomerResponse
import com.ferraobox.qamyapp.dto.OrderRequest
import com.ferraobox.qamyapp.dto.OrderResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.concurrent.CompletableFuture
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@Component
open class OrderController(
    private val useCaseExecutor: UseCaseExecutor,
    private val createOrderUseCase: CreateOrderUseCase,
    private val getOrderUseCase: GetOrderUseCase,
    private val getCustomerOrderUseCase: GetCustomerOrderUseCase,
    private val deleteOrderUseCase: DeleteOrderUseCase,
    private val payOrderUseCase: PayOrderUseCase,
    private val deliveryOrderUseCase: DeliveryOrderUseCase,
    private val createOrderInputMapper: CreateOrderInputMapper,
) : OrderResource {

    override fun create(
        @CurrentUser userPrincipal: UserPrincipal?,
        httpServletRequest: HttpServletRequest?,
        @RequestBody orderRequest: @Valid OrderRequest?
    ): CompletableFuture<ResponseEntity<OrderResponse?>?> {
        return useCaseExecutor.execute(
            createOrderUseCase,
            createOrderInputMapper.map(orderRequest!!, userPrincipal!!)
        ) { outputValues ->
            val location = ServletUriComponentsBuilder
                .fromContextPath(httpServletRequest!!)
                .path("/order/{id}")
                .buildAndExpand(outputValues.order!!.id.number)
                .toUri()
            ResponseEntity.created(location).body(outputValues.order.mapToDto())
        }
    }

    override fun getById(@PathVariable id: Long): CompletableFuture<OrderResponse?> {
        return useCaseExecutor.execute(
            getOrderUseCase,
            GetOrderUseCase.InputValues(id = Identity(id))
        ) { outputValues -> outputValues.order!!.mapToDto() }
    }

    override fun getCustomerById(@PathVariable id: Long): CompletableFuture<CustomerResponse?> {
        return useCaseExecutor.execute(
            getCustomerOrderUseCase,
            GetCustomerOrderUseCase.InputValues(id = Identity(id))
        ) { outputValues -> outputValues.customer!!.mapToDto() }
    }

    override fun delete(@PathVariable id: Long): CompletableFuture<ApiResponse?> {
        return useCaseExecutor.execute(
            deleteOrderUseCase,
            UpdateOrderUseCase.InputValues(id = Identity(id))
        ) { ApiResponse(true, "Order successfully canceled") }
    }

    override fun pay(@PathVariable id: Long): CompletableFuture<ApiResponse?> {
        return useCaseExecutor.execute(
            payOrderUseCase,
            UpdateOrderUseCase.InputValues(id = Identity(id))
        ) { ApiResponse(true, "Order successfully paid") }
    }

    override fun delivery(@PathVariable id: Long): CompletableFuture<ApiResponse?> {
        return useCaseExecutor.execute(
            deliveryOrderUseCase,
            UpdateOrderUseCase.InputValues(id = Identity(id))
        ) { ApiResponse(true, "Order successfully delivered") }
    }
}