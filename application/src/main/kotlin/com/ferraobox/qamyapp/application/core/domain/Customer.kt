package com.ferraobox.qamyapp.application.core.domain

data class Customer(
    override var id: Identity,
    var name: String,
    var email: String,
    var address: String,
    var password: String,
) : BaseDomainEntity()