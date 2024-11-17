package com.edwinyosua.core.domain.usecase

import com.edwinyosua.core.data.Resource
import com.edwinyosua.core.domain.model.Tourism
import com.edwinyosua.core.domain.repository.ITourismRepository
import kotlinx.coroutines.flow.Flow

class TourismInteractor(private val repository: ITourismRepository) : TourismUseCase {
    override fun getAllTourism(): Flow<Resource<List<Tourism>>> =
        repository.getAllTourism()

    override fun getFavoriteTourism(): Flow<List<Tourism>> = repository.getFavoriteTourism()

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) =
        repository.setFavoriteTourism(tourism, state)
}