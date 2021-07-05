package com.ferraobox.qamyapp.application.database.entities

import com.ferraobox.qamyapp.application.core.domain.Identity

object IdConverter {
    fun convertId(id: Identity?): Long? {
        return id?.number
    }
}