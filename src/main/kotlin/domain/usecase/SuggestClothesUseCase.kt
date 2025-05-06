package org.example.domain.usecase

import org.example.domain.entity.ClothesSuggestion
import org.example.domain.repository.WeatherRepository

class SuggestClothesUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(city: String): Result<ClothesSuggestion> {
            return Result.success(ClothesSuggestion("", "", ""))
        }

}