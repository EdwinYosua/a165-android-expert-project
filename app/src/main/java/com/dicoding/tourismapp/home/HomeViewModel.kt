package com.dicoding.tourismapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.edwinyosua.core.domain.usecase.TourismUseCase

class HomeViewModel(tourismRepository: TourismUseCase) : ViewModel() {

    val tourism = tourismRepository.getAllTourism().asLiveData()

}

