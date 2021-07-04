package com.ferraobox.qamyapp.application.database.repositories.impl

import com.ferraobox.qamyapp.application.core.domain.Cousine
import com.ferraobox.qamyapp.application.core.mappers.CousineDomainDbMapper.mapToDomain
import com.ferraobox.qamyapp.application.core.repositories.ICousineRepository
import com.ferraobox.qamyapp.application.database.repositories.DbCousineRepository
import org.springframework.stereotype.Repository
import java.util.stream.Collectors

@Repository
class CousineRepositoryImpl(
    private val dbCousineRepository: DbCousineRepository,
) : ICousineRepository {

    override fun all(): List<Cousine> {
        return dbCousineRepository
            .findAll()
            .parallelStream()
            .map { c -> c?.mapToDomain() }.collect(Collectors.toList<Cousine>())
    }

    override fun searchByName(search: String): List<Cousine> {
        return dbCousineRepository
            .findByNameContainingIgnoreCase(search)
            .parallelStream()
            .map { c -> c?.mapToDomain() }.collect(Collectors.toList<Cousine>())
    }
}