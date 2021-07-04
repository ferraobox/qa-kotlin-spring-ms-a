package com.ferraobox.qamyapp.dto


data class OrderItemResponse (
    var product: ProductResponse,
    var price: Double,
    var quantity: Int,
    var total: Double
)