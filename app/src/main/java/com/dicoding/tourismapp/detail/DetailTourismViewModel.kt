package com.dicoding.tourismapp.detail

import androidx.lifecycle.ViewModel
import com.edwinyosua.core.domain.model.Tourism
import com.edwinyosua.core.domain.usecase.TourismUseCase

class DetailTourismViewModel(private val tourismRepository: TourismUseCase) : ViewModel() {
    fun setFavoriteTourism(tourism: Tourism, newStatus: Boolean) =
        tourismRepository.setFavoriteTourism(tourism, newStatus)
}

