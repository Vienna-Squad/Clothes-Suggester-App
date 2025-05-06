package org.example.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.example.data.model.WeatherApiResponse
import org.example.domain.InvalidCityException

class WeatherServiceImpl(
    private val client: HttpClient
) : WeatherService {
        override suspend fun getWeather(city: String): Result<WeatherApiResponse> {
            return fetchWeather(
                city = city,
                url = "${ApiConfig.BASE_URL}${ApiConfig.CURRENT_WEATHER_ENDPOINT}"
            )
        }

    private suspend fun fetchWeather(
        city: String,
        url: String,
    ): Result<WeatherApiResponse> {
        return try {
            if (city.isBlank()) throw InvalidCityException("City name cannot be empty")
            val response = client.get(url) {
                parameter("key", ApiConfig.API_KEY)
                parameter("q", city)
            }.body<WeatherApiResponse>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}

