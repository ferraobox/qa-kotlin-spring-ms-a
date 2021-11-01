package com.ferraobox.qamyapp.dto

import io.swagger.v3.oas.annotations.media.Schema

data class SignUpRequest (
    @Schema(description = "Name", example = "Carlos Ferrao")
    var name: String,

    @Schema(description = "Email", example = "c@f.com")
    var email: String,

    @Schema(description = "Address", example = "aadfadf")
    var address: String,

    @Schema(description = "Password", example = "aaaaaaaaq")
    var password: String
)
