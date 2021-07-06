package com.ferraobox.qamyapp.application.presenter.rest.api.order

import com.ferraobox.qamyapp.application.presenter.usecases.security.CurrentUser
import com.ferraobox.qamyapp.application.presenter.usecases.security.UserPrincipal
import com.ferraobox.qamyapp.dto.ApiResponse
import com.ferraobox.qamyapp.dto.CustomerResponse
import com.ferraobox.qamyapp.dto.OrderRequest
import com.ferraobox.qamyapp.dto.OrderResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("/order")
interface OrderResource {
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    fun create(
        @CurrentUser userPrincipal: UserPrincipal?,
        httpServletRequest: HttpServletRequest?,
        @RequestBody orderRequest: @Valid OrderRequest?
    ): CompletableFuture<ResponseEntity<OrderResponse?>?>?

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    fun getById(@PathVariable id: Long): CompletableFuture<OrderResponse?>?

    @GetMapping("/{id}/customer")
    @PreAuthorize("hasRole('USER')")
    fun getCustomerById(@PathVariable id: Long): CompletableFuture<CustomerResponse?>?

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    fun delete(@PathVariable id: Long): CompletableFuture<ApiResponse?>?

    @PostMapping("/{id}/payment")
    @PreAuthorize("hasRole('USER')")
    fun pay(@PathVariable id: Long): CompletableFuture<ApiResponse?>?

    @PostMapping("/{id}/delivery")
    @PreAuthorize("hasRole('USER')")
    fun delivery(@PathVariable id: Long): CompletableFuture<ApiResponse?>?
}