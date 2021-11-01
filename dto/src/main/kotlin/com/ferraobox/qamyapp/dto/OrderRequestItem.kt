package com.ferraobox.qamyapp.dto;

import io.swagger.v3.oas.annotations.media.Schema

data class OrderRequestItem(
    @Schema(description = "Id", example = "1")
    var id: Long,
    @Schema(description = "Quantity", example = "1")
    var quantity: Int
)
