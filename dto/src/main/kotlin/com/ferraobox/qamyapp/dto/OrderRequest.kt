package com.ferraobox.qamyapp.dto

import io.swagger.v3.oas.annotations.media.Schema

data class OrderRequest (

    @Schema(description = "Product price", example = "1")
    var storeId: Long,
    @Schema(description = "List of items", example = "[\n" +
            "    {\n" +
            "      \"product\": {\n" +
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
            "      },\n" +
            "      \"price\": 10.95,\n" +
            "      \"quantity\": 1,\n" +
            "      \"total\": 10.95\n" +
            "    }\n" +
            "  ]")
    var orderItems: List<OrderRequestItem>
)
