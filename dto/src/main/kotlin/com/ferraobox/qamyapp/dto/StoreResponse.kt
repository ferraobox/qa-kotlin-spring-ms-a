package com.ferraobox.qamyapp.dto

data class StoreResponse (
    var id: Long,
    var name: String,
    var address: String,
    var cousine: CousineResponse,
    var products: List<ProductResponse>
)