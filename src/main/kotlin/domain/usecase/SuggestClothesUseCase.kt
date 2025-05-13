package org.example.domain.usecase

import org.example.domain.entity.ClothesSuggestion
import org.example.domain.repository.ClothesRepository
import org.example.domain.repository.WeatherRepository

class SuggestClothesUseCase(
    private val weatherRepository: WeatherRepository,
    private val clothesRepository: ClothesRepository
) {
    suspend operator fun invoke(city: String): ClothesSuggestion {
        val weather = weatherRepository.getWeather(city)
        return clothesRepository.getClothingSuggestionByTemperature(weather.temperature)
    }
}
