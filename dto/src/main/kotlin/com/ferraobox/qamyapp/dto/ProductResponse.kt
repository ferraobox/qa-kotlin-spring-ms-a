package com.ferraobox.qamyapp.dto

import io.swagger.v3.oas.annotations.media.Schema

data class ProductResponse (
    @Schema(description = "Id", example = "1")
    var id: Long,

    @Schema(description = "Name", example = "Shrimp Tempura")
    var name: String,

    @Schema(description = "Description", example = "Fresh shrimp battered and deep fried until golden brown")
    var description: String,

    @Schema(description = "Price", example = "10.95")
    var price: Double,

    @Schema(description = "Store", example = "{\n" +
            "          \"id\": 1,\n" +
            "          \"name\": \"Hai Shang\",\n" +
            "          \"address\": \"2991 Pembina Hwy, Winnipeg, Manitoba R3T 2H5, Canada\",\n" +
            "          \"cousine\": {\n" +
            "            \"id\": 1,\n" +
            "            \"name\": \"Chinese\"\n" +
            "          }\n" +
            "        }")
    var store: StoreResponse,
)
