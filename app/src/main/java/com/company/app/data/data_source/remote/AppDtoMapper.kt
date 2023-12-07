package com.company.app.data.data_source.remote


import com.company.app.domain.DomainMapper
import com.company.app.domain.model.AppDomainModel


class AppDtoMapper : DomainMapper<AppDto, AppDomainModel> {
    override fun mapToDomainModel(model: AppDto): AppDomainModel {
        return AppDomainModel(
            id = model.id,
            title = model.title
        )
    }

    override fun mapFromDomainModel(domainModel: AppDomainModel): AppDto {
        return AppDto(
            id = domainModel.id,
            title = domainModel.title
        )
    }

    fun fromEntityList(initial: List<AppDto>): List<AppDomainModel> {
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<AppDomainModel>): List<AppDto> {
        return initial.map { mapFromDomainModel(it) }
    }


}






