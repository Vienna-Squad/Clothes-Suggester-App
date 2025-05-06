package org.example.domain.usecase

import org.example.domain.repository.WeatherRepository

class SuggestClothesUseCase(
    private val weatherRepository: WeatherRepository
) {

}