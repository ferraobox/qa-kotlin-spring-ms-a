package com.ferraobox.qamyapp.application.core.repositories

import com.ferraobox.qamyapp.application.core.domain.Cousine

interface ICousineRepository {
    fun all(): List<Cousine>
    fun searchByName(search: String?): List<Cousine>
}
