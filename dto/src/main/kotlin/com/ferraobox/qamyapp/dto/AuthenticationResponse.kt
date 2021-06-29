package com.ferraobox.qamyapp.dto

data class AuthenticationResponse (
    private val success: Boolean = true,
    private val token: String?
)