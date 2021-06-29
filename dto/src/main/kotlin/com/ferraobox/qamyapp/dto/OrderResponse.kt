package com.ferraobox.qamyapp.dto;

import java.time.Instant

data class OrderResponse (
    private val id: Long?,
    private val date: Instant?,
    private val store: StoreResponse?,
    private val contact: String?,
    private val total: Double?,
    private val status: Status?,
    private val lastUpdate: Instant?,
    private val orderItems: List<OrderItemResponse>?
)