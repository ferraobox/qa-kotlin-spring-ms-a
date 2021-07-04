package com.ferraobox.qamyapp.dto

data class SignUpRequest (
    var name: String,
    var email: String,
    var address: String,
    var password: String
)