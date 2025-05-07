package org.example.domain.usecase

import org.example.domain.entity.ClothesSuggestion
import org.example.domain.repository.WeatherRepository

class SuggestClothesUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(city: String): ClothesSuggestion {

        val weather = weatherRepository.getWeather(city)
        return when {

            weather.temperature <= -5.0 -> ClothesSuggestion(
                top = "Heavy Jacket",
                bottom = "Thermal Pants",
                accessories = "Gloves"
            )

            weather.temperature <= 10.0 -> ClothesSuggestion(
                top = "Sweater",
                bottom = "Jeans",
                accessories = "Scarf"
            )

            weather.temperature <= 20.0 -> ClothesSuggestion(
                top = "Jacket",
                bottom = "Pants",
                accessories = "Umbrella"
            )

            weather.temperature in 25.0..50.0 -> ClothesSuggestion(
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
