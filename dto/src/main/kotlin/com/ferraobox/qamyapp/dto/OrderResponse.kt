package com.ferraobox.qamyapp.dto;

import io.swagger.v3.oas.annotations.media.Schema
import java.time.Instant

data class OrderResponse (

    @Schema(description = "Id", example = "1")
    var id: Long,
    @Schema(description = "Date", example = "2021-10-31T23:53:38.621785Z")
    var date: Instant,
    @Schema(description = "Customer", example = "{\n" +
            "    \"id\": 2,\n" +
            "    \"name\": \"Carlos Ferrao\",\n" +
            "    \"email\": \"c@f.com\",\n" +
            "    \"address\": \"aadfadf\"\n" +
            "  }")
    var customer: CustomerResponse,

    @Schema(description = "Store", example = "{\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"Hai Shang\",\n" +
            "    \"address\": \"2991 Pembina Hwy, Winnipeg, Manitoba R3T 2H5, Canada\",\n" +
            "    \"cousine\": {\n" +
            "      \"id\": 1,\n" +
            "      \"name\": \"Chinese\"\n" +
            "    }\n" +
            "  }")
    var store: StoreResponse,

    @Schema(description = "Contact", example = "Carlos")
    var contact: String,

    @Schema(description = "Total", example = "10.95")
    var total: Double,

    @Schema(description = "Status", example = "OPEN")
    var status: Status,

    @Schema(description = "Last Update", example = "2021-10-31T23:53:38.621785Z")
    var lastUpdate: Instant,

    @Schema(description = "Order Items", example = "[\n" +
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
    var orderItems: List<OrderItemResponse>
)
