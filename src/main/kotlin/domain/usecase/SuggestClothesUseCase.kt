package org.example.domain.usecase

import org.example.domain.entity.ClothesSuggestion
import org.example.domain.repository.WeatherRepository

class SuggestClothesUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(city: String): ClothesSuggestion {

        val weather = weatherRepository.getWeather(city)
        return when (weather.temperature) {
            in -30.0..4.9 -> ClothesSuggestion(
                top = "Heavy Jacket",
                bottom = "Thermal Pants",
                accessories = "Gloves"
            )
            in 5.0..19.9 -> ClothesSuggestion(
                top = "Sweater",
                bottom = "Jeans",
                accessories = "Scarf"
            )
            in 20.0..24.9 -> ClothesSuggestion(
                top = "Jacket",
                bottom = "Pants",
                accessories = "Umbrella"
            )
            in 25.0..50.0 -> ClothesSuggestion(
                top = "T-shirt",
                bottom = "Shorts",
                accessories = "Sunglasses"
            )
            else -> ClothesSuggestion(
                top = "Shirt",
                bottom = "Shorts",
                accessories = "None"
            )
        }
    }
}