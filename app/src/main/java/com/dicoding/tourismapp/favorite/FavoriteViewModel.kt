package com.dicoding.tourismapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.tourismapp.core.domain.usecase.TourismUseCase

class FavoriteViewModel(tourismRepository: TourismUseCase) : ViewModel() {

    val favoriteTourism = tourismRepository.getFavoriteTourism().asLiveData()

}

