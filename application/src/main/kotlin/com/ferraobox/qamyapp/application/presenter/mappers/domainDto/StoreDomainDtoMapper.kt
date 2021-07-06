package com.ferraobox.qamyapp.application.presenter.mappers.domainDto

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Store
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.CousineDomainDtoMapper.mapToDomain
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.CousineDomainDtoMapper.mapToDto
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.ProductDomainDtoMapper.mapToDomain
import com.ferraobox.qamyapp.application.presenter.mappers.domainDto.ProductDomainDtoMapper.mapToDto
import com.ferraobox.qamyapp.dto.StoreResponse
import org.springframework.stereotype.Component

@Component
object StoreDomainDtoMapper {

    fun Store.mapToDto(): StoreResponse {
        return StoreResponse(
            id = this.id.number,
            name = this.name,
            address = this.address,
            cousine = this.cousine.mapToDto()
        )
    }

    fun List<Store>.mapToDto(): List<StoreResponse> {
        val storeListResponse = ArrayList<StoreResponse>()
        forEach { store -> storeListResponse.add(store.mapToDto()) }
        return storeListResponse
    }


    fun StoreResponse.mapToDomain(): Store {
        return Store(
            id = Identity(this.id),
            name = this.name,
            address = this.address,
            cousine = this.cousine.mapToDomain()
        )
    }

    fun List<StoreResponse>.mapToDomain(): List<Store> {
        val storeList = ArrayList<Store>()
        forEach { store -> storeList.add(store.mapToDomain()) }
        return storeList
    }
}
