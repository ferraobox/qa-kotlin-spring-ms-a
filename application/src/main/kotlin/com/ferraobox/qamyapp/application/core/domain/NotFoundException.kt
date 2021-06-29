package com.ferraobox.qamyapp.application.core.domain

class NotFoundException : DomainException {
    constructor(message: String?) : super(message) {}
    constructor(messageFormat: String?, vararg args: Any?) : super(String.format(messageFormat!!, *args)) {}
}