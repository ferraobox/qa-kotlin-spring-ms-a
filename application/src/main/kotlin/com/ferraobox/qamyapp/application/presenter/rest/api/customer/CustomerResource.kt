package com.ferraobox.qamyapp.application.presenter.rest.api.customer

import com.ferraobox.qamyapp.dto.AuthenticationResponse
import com.ferraobox.qamyapp.dto.CustomerResponse
import com.ferraobox.qamyapp.dto.SignInRequest
import com.ferraobox.qamyapp.dto.SignUpRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid


@RestController
@RequestMapping("/Customer")
interface CustomerResource {
    @PostMapping
    fun signUp(
        @RequestBody request: @Valid SignUpRequest?, httpServletRequest: HttpServletRequest?
    ): CompletableFuture<ResponseEntity<CustomerResponse?>?>?

    @PostMapping("/auth")
    fun signIn(@RequestBody request: @Valid SignInRequest?): CompletableFuture<ResponseEntity<AuthenticationResponse?>?>?
}