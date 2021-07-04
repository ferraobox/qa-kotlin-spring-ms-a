package com.ferraobox.qamyapp.dto

data class OrderRequest (
    var storeId: Long,
    var orderItems: List<OrderRequestItem>
)