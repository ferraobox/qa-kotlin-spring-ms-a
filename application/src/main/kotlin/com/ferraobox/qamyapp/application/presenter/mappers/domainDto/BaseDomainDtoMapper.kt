package com.ferraobox.qamyapp.application.presenter.mappers.domainDto


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
    fun mapToDto(eList: List<F>?): List<T>?

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