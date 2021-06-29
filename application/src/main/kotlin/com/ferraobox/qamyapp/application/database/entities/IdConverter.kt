package com.ferraobox.qamyapp.application.database.entities

import com.ferraobox.qamyapp.application.core.domain.Identity

object IdConverter {
    fun convertId(id: Identity?): Long? {
        return if (id != null && id.number !== Long.MIN_VALUE) {
            id.number
        } else null
    }
}