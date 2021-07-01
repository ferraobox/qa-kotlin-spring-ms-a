package com.ferraobox.qamyapp.application.presenter.mappers.domainDto

import com.ferraobox.qamyapp.application.core.domain.Cousine
import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.database.entities.IdConverter
import com.ferraobox.qamyapp.dto.CousineResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.springframework.stereotype.Component

@Component
@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring",
    imports = [IdConverter::class, Identity::class],
    uses = []
)
interface CousineDomainDtoMapper : BaseDomainDtoMapper<Cousine?, CousineResponse?> {
    @Mappings(
        Mapping(expression = "java(cousine.getId().getNumber())", target = "id"),
        Mapping(source = "name", target = "name")
    )
    override fun mapToDto(cousine: Cousine?): CousineResponse?
    override fun mapToDto(cousine: List<Cousine?>, list: Boolean): List<CousineResponse?>

    @Mappings(
        Mapping(expression = "java(new Identity(cousine.getId()))", target = "id"),
        Mapping(source = "name", target = "name")
    )
    override fun mapToDomain(cousine: CousineResponse?): Cousine?
    override fun mapToDomain(cousine: List<CousineResponse?>, list: Boolean): List<Cousine?>
}