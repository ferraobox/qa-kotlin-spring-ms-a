package com.ferraobox.qamyapp.dto

import io.swagger.v3.oas.annotations.media.Schema

data class CousineResponse (
    @Schema(description = "Id", example = "1")
    var id: Long,
    @Schema(description = "Name", example = "Chinese")
    var name: String
)
