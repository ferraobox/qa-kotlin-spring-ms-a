package com.ferraobox.qamyapp.dto

import io.swagger.v3.oas.annotations.media.Schema

data class AuthenticationResponse (
    @Schema(description = "Success", example = "true")
    var success: Boolean = true,
    @Schema(description = "Token", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjM1NzI0NDE4LCJleHAiOjE2MzYzMjkyMTh9.fVhO_RHGyBUgpmENYwxFKikTppochKZIyJXv15n6yNqKVqj7oENBtxezmBdMe-BfcNfvpXF_lqW-RpIt1FGQ6A")
    var token: String?
)
