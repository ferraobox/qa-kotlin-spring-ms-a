package com.ferraobox.qamyapp.dto

import io.swagger.v3.oas.annotations.media.Schema


data class OrderItemResponse (
    @Schema(description = "Product", example = "{\n" +
            "        \"id\": 1,\n" +
            "        \"name\": \"Shrimp Tempura\",\n" +
            "        \"description\": \"Fresh shrimp battered and deep fried until golden brown\",\n" +
            "        \"price\": 10.95,\n" +
            "        \"store\": {\n" +
            "          \"id\": 1,\n" +
            "          \"name\": \"Hai Shang\",\n" +
            "          \"address\": \"2991 Pembina Hwy, Winnipeg, Manitoba R3T 2H5, Canada\",\n" +
            "          \"cousine\": {\n" +
            "            \"id\": 1,\n" +
            "            \"name\": \"Chinese\"\n" +
            "          }\n" +
            "        }\n" +
            "      }")
    var product: ProductResponse,
    @Schema(description = "Product price", example = "10.95")
    var price: Double,
    @Schema(description = "Product price", example = "1")
    var quantity: Int,
    @Schema(description = "Product price", example = "10.95")
    var total: Double
)
