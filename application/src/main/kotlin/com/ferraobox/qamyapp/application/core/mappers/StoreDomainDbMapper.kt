package com.ferraobox.qamyapp.application.core.mappers

import com.ferraobox.qamyapp.application.core.domain.Identity
import com.ferraobox.qamyapp.application.core.domain.Store
import com.ferraobox.qamyapp.application.database.entities.IdConverter
import com.ferraobox.qamyapp.application.database.entities.StoreDb
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy
import org.springframework.stereotype.Component
import java.util.*

@Component
@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring",
    imports = [Arrays::class, HashSet::class, IdConverter::class, Identity::class],
    uses = [CousineDomainDbMapper::class]
)
interface StoreDomainDbMapper : BaseDomainDbMapper<Store?, StoreDb?> {
    @Mappings(
    Mapping(expression = "java(IdConverter.convertId(store.getId()))", target = "id"),
    Mapping(source = "name", target = "name"),
    Mapping(source = "address", target = "address"),
    Mapping(source = "cousine", target = "cousine"),
    )
    override fun mapToDb(store: Store?): StoreDb?
    override fun mapToDb(store: List<Store?>?): List<StoreDb?>?

    @Mappings(
    Mapping(expression = "java(new Identity(store.getId()))", target = "id"),
    Mapping(source = "name", target = "name"),
    Mapping(source = "address", target = "address"),
    Mapping(source = "cousine", target = "cousine")
    )
    override fun mapToDomain(store: StoreDb?): Store?
    override fun mapToDomain(store: List<StoreDb?>?): List<Store?>?
}