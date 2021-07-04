package com.ferraobox.qamyapp.dto

data class ProductResponse (
    var id: Long,
    var name: String,
    var description: String,
    var price: Double,
    var store: StoreResponse,
)