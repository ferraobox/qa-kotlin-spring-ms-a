package com.ferraobox.qamyapp.application.presenter.mappers.domainDto

import com.ferraobox.qamyapp.application.core.domain.BaseDomainEntity

/**
 * Convert from Domain(F) to Dto(T)
 */
interface BaseDomainDtoMapper<F, T> {
    /**
     * Maps from Domain object to Db object.
     * @param from  source
     * @return  to  target
     */
    fun mapToDto(from: F): T

    /**
     * Maps list of objects
     * @param eList the list
     * @return Transformed list.
     */
    fun mapToDto(eList: List<F>, list: Boolean): List<T>

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
    fun mapToDomain(eList: List<T>, list: Boolean): List<F>
}