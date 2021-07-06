package com.ferraobox.qamyapp.application.core.domain

data class Identity(
    val number: Long = Long.MIN_VALUE
) {
    companion object {
        fun nothing(): Identity {
            return Identity(Long.MIN_VALUE)
        }
    }
}