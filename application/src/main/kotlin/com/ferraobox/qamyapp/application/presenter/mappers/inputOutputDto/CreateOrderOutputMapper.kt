package com.ferraobox.qamyapp.application.presenter.mappers.inputOutputDto

import com.ferraobox.qamyapp.application.core.domain.Order
import com.ferraobox.qamyapp.dto.ApiResponse
import org.springframework.stereotype.Component

@Component
object CreateOrderOutputMapper {
    fun Order.map(): ApiResponse{
        return ApiResponse(success = true, message = "order created successfully" )
    }
}
