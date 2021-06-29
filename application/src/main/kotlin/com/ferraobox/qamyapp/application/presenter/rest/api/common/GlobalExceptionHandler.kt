package com.ferraobox.qamyapp.application.presenter.rest.api.common

import com.ferraobox.qamyapp.application.core.domain.DomainException
import com.ferraobox.qamyapp.application.core.domain.EmailAlreadyUsedException
import com.ferraobox.qamyapp.dto.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [AuthenticationException::class])
    fun handleAuthenticationException(ex: AuthenticationException): ResponseEntity<ApiResponse> {
        return ResponseEntity<ApiResponse>(ApiResponse(false, ex.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [EmailAlreadyUsedException::class])
    fun handleEmailAlreadyUsedException(ex: EmailAlreadyUsedException): ResponseEntity<ApiResponse> {
        return ResponseEntity<ApiResponse>(ApiResponse(false, ex.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [DomainException::class])
    fun handleDomainException(ex: DomainException): ResponseEntity<ApiResponse> {
        return ResponseEntity<ApiResponse>(ApiResponse(false, ex.message), HttpStatus.NOT_FOUND)
    }
}