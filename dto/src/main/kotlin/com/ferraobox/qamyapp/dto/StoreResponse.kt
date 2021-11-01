package com.ferraobox.qamyapp.dto

import io.swagger.v3.oas.annotations.media.Schema

data class StoreResponse (
    @Schema(description = "Id", example = "1")
    var id: Long,

    @Schema(description = "Name", example = "Hai Shang")
    var name: String,

    @Schema(description = "Address", example = "2991 Pembina Hwy, Winnipeg, Manitoba R3T 2H5, Canada")
    var address: String,

    @Schema(description = "Cousine", example = "{\n" +
            "            \"id\": 1,\n" +
            "            \"name\": \"Chinese\"\n" +
            "          }")
    var cousine: CousineResponse,
)
