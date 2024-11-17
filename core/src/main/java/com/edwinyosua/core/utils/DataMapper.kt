package com.edwinyosua.core.utils

import com.edwinyosua.core.data.source.remote.response.TourismResponse
import com.edwinyosua.core.domain.model.Tourism

object DataMapper {
    fun mapResponsesToEntities(input: List<TourismResponse>): List<com.edwinyosua.core.data.source.local.entity.TourismEntity> {
        val tourismList = ArrayList<com.edwinyosua.core.data.source.local.entity.TourismEntity>()
        input.map {
            val tourism = com.edwinyosua.core.data.source.local.entity.TourismEntity(
                tourismId = it.id,
                description = it.description,
                name = it.name,
                address = it.address,
                latitude = it.latitude,
                longitude = it.longitude,
                like = it.like,
                image = it.image,
                isFavorite = false
            )
            tourismList.add(tourism)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<com.edwinyosua.core.data.source.local.entity.TourismEntity>): List<Tourism> =
        input.map {
            Tourism(
                tourismId = it.tourismId,
                description = it.description,
                name = it.name,
                address = it.address,
                latitude = it.latitude,
                longitude = it.longitude,
                like = it.like,
                image = it.image,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Tourism) =
        com.edwinyosua.core.data.source.local.entity.TourismEntity(
            tourismId = input.tourismId,
            description = input.description,
            name = input.name,
            address = input.address,
            latitude = input.latitude,
            longitude = input.longitude,
            like = input.like,
            image = input.image,
            isFavorite = input.isFavorite
        )
}