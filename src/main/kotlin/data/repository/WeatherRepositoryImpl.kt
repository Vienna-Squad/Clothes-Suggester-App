package org.example.data.repository

import org.example.data.model.WeatherApiResponse
import org.example.data.remote.WeatherService
import org.example.domain.InvalidCityException
import org.example.domain.entity.Weather
import org.example.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherService: WeatherService
):WeatherRepository {
    override suspend fun getWeather(city: String): Result<Weather> {
        TODO("Not yet implemented")
}
}
