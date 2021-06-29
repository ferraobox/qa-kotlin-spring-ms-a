package com.ferraobox.qamyapp.dto

data class OrderItemResponse (
    private val name: String?,
    private val price: Double?,
    private val quantity: Int?,
    private val total: Double?
)