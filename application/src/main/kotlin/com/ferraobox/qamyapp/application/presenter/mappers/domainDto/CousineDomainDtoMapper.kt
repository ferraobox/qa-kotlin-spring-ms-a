package com.ferraobox.qamyapp.application.presenter.mappers.domainDto

import com.ferraobox.qamyapp.application.core.domain.Cousine
import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.dto.CousineResponse
import org.springframework.stereotype.Component

@Component

object CousineDomainDtoMapper {

    fun Cousine.mapToDto(): CousineResponse {
        return CousineResponse(
            id = this.id.number,
            name = this.name
        )
    }

    fun List<Cousine>.mapToDto(): List<CousineResponse> {
        val cousineListResponse = ArrayList<CousineResponse>()
        forEach { cousine -> cousineListResponse.add(cousine.mapToDto()) }
        return cousineListResponse
    }

    fun CousineResponse.mapToDomain(): Cousine {
        return Cousine(
            id = Identity(this.id),
            name = this.name
        )
    }

    fun List<CousineResponse>.mapToDomain(): List<Cousine> {
        val cousineList = ArrayList<Cousine>()
        forEach { cousine -> cousineList.add(cousine.mapToDomain()) }
        return cousineList
    }


}