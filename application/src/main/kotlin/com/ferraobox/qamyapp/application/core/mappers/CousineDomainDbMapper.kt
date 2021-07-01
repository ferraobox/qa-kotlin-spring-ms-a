package com.ferraobox.qamyapp.application.core.mappers

import com.ferraobox.qamyapp.application.core.domain.Cousine
import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.database.entities.CousineDb
import com.ferraobox.qamyapp.application.database.entities.IdConverter
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import java.util.*

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring",
    imports = [Arrays::class, IdConverter::class, Identity::class],
    uses = []
)
interface CousineDomainDbMapper : BaseDomainDbMapper<Cousine?, CousineDb?> {

    @Mappings(
        Mapping(expression = "java(IdConverter.INSTANCE.convertId(cousine.getId()))", target = "id"),
        Mapping(source = "name", target = "name")
    )
    override fun mapToDb(cousine: Cousine?): CousineDb?
    override fun mapToDb(cousine: List<Cousine?>?): List<CousineDb?>?

    @Mappings(
        Mapping(expression = "java(new Identity(cousine.getId()))", target = "id"),
        Mapping(source = "name", target = "name")
    )
    override fun mapToDomain(cousine: CousineDb?): Cousine?
    override fun mapToDomain(cousine: List<CousineDb?>?): List<Cousine?>?
}