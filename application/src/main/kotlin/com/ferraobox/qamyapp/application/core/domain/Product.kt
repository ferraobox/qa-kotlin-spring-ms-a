package com.ferraobox.qamyapp.application.core.domain

data class Product(
    override var id: Identity,
    var name: String,
    var description: String,
    var price: Double,
    var store: Store,
) : BaseDomainEntity()