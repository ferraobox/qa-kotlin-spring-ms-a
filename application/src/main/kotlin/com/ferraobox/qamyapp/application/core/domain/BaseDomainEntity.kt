package com.ferraobox.qamyapp.application.core.domain

open class BaseDomainEntity(
    open var id: Identity
) {
    constructor() : this(Identity())
}
