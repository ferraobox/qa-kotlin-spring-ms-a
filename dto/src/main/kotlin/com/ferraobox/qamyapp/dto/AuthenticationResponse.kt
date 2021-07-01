package com.ferraobox.qamyapp.dto

data class AuthenticationResponse (
    var success: Boolean = true,
    var token: String?
)