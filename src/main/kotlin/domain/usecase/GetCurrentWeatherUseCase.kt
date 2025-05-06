package org.example.domain.usecase

import org.example.domain.entity.Weather
import org.example.domain.repository.WeatherRepository

class GetCurrentWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(city: String): Result<Weather> {
        return weatherRepository.getWeather(city)
    }
}