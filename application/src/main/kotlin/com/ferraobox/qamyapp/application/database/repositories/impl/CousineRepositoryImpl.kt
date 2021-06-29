package com.ferraobox.qamyapp.application.database.repositories.impl

import com.ferraobox.qamyapp.application.core.domain.Cousine
import com.ferraobox.qamyapp.application.core.mappers.CousineDomainDbMapper
import com.ferraobox.qamyapp.application.core.repositories.ICousineRepository
import com.ferraobox.qamyapp.application.database.repositories.DbCousineRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.stream.Collectors
import kotlin.streams.toList

@Repository
class CousineRepositoryImpl(
    private val dbCousineRepository: DbCousineRepository,
    @Autowired
    private val cousineDomainDbMapper: CousineDomainDbMapper
) : ICousineRepository {

    override fun all(): List<Cousine>{
        return dbCousineRepository
            .findAll()
            .parallelStream()
            .map { c -> cousineDomainDbMapper.mapToDomain(c) }.collect(Collectors.toList<Cousine>())
    }


    override fun searchByName(search: String?): List<Cousine> {
        return dbCousineRepository
            .findByNameContainingIgnoreCase(search)
            .parallelStream()
            .map { c -> cousineDomainDbMapper.mapToDomain(c) }.collect(Collectors.toList<Cousine>())
    }
}