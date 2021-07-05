package com.ferraobox.qamyapp.application.database.repositories.impl

import com.ferraobox.qamyapp.application.core.domain.Cousine
import com.ferraobox.qamyapp.application.core.mappers.CousineDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.core.repositories.ICousineRepository
import com.ferraobox.qamyapp.application.database.repositories.DbCousineRepository
import org.springframework.stereotype.Repository
import java.util.stream.Collectors

@Repository
open class CousineRepositoryImpl(
    private val repository: DbCousineRepository,
) : ICousineRepository {

    override fun all(): List<Cousine> {
        return repository
            .findAll()
            .parallelStream()
            .map { c -> c?.mapToDomain() }.collect(Collectors.toList<Cousine>())
    }

    override fun searchByName(search: String): List<Cousine> {
        return repository
            .findByNameContainingIgnoreCase(search)
            .parallelStream()
            .map { c -> c?.mapToDomain() }.collect(Collectors.toList<Cousine>())
    }
}