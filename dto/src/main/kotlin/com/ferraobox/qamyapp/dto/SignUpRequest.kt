package com.ferraobox.qamyapp.dto


data class SignUpRequest (
    private val name: String?,
    private val email: String?,
    private val address: String?,
    private val password: String?
)