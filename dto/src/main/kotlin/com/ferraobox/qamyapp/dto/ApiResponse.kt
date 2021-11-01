package com.ferraobox.qamyapp.dto

import io.swagger.v3.oas.annotations.media.Schema

data class ApiResponse (
    @Schema(description = "Success", example = "true")
    var success: Boolean?,
    @Schema(description = "Message", example = "Message MOCK")
    var message: String?
)
