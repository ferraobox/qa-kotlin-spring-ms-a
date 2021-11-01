package com.ferraobox.qamyapp.dto

import io.swagger.v3.oas.annotations.media.Schema

data class CustomerResponse (
    @Schema(description = "Id", example = "2")
    var id: Long,
    @Schema(description = "Name", example = "Carlos Ferrao")
    var name: String,
    @Schema(description = "Email", example = "c@f.com")
    var email: String,
    @Schema(description = "Address", example = "aadfadf")
    var address: String
)
