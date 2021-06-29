package com.ferraobox.qamyapp.dto

data class OrderRequest (
    private val storeId: Long?,
    private val orderItems: List<OrderRequestItem>?
)