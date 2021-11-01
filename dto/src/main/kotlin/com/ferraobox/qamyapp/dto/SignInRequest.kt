package com.ferraobox.qamyapp.dto

import io.swagger.v3.oas.annotations.media.Schema

data class SignInRequest (
    @Schema(description = "Id of cousine", example = "c@f.com")
    var email: String,
    @Schema(description = "Id of cousine", example = "aaaaaaaaq")
    var password: String
)
