package com.edwinyosua.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.edwinyosua.core.domain.usecase.TourismUseCase

class MapsViewModel(tourismUseCase: TourismUseCase) : ViewModel() {

    val tourism = tourismUseCase.getAllTourism().asLiveData()
}