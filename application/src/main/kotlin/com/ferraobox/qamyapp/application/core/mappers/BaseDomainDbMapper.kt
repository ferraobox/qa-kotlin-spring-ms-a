package com.ferraobox.qamyapp.application.core.mappers

import com.ferraobox.qamyapp.application.core.domain.BaseDomainEntity
import com.ferraobox.qamyapp.application.database.entities.BaseDbEntity

/**
 * Convert from Domain(F) to Db(T)
 */
interface BaseDomainDbMapper<F : BaseDomainEntity?, T : BaseDbEntity?> {
    /**
     * Maps from Domain object to Db object.
     * @param from  source
     * @return  to  target
     */
    fun mapToDb(from: F): T

    /**
     * Maps list of objects
     * @param eList the list
     * @return Transformed list.
     */
    fun mapToDb(eList: List<F>?): List<T>?

    /**
     * Maps from Db object to Domain object.
     * @param from  source
     * @return  to  target
     */
    fun mapToDomain(from: T): F

    /**
     * Maps list of objects
     * @param eList the list
     * @return Transformed list.
     */
    fun mapToDomain(eList: List<T>?): List<F>?
}