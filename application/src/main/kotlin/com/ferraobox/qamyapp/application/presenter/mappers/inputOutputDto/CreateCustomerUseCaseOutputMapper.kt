package com.ferraobox.qamyapp.application.presenter.mappers.inputOutputDto

import com.ferraobox.qamyapp.application.core.domain.Customer
import com.ferraobox.qamyapp.dto.ApiResponse
import org.springframework.stereotype.Component

@Component
object CreateCustomerUseCaseOutputMapper {
    fun Customer.map(): ApiResponse{
        return ApiResponse(success = true, message = "registered successfully" )
    }
}