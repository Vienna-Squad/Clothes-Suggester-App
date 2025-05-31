package org.example.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.example.data.model.WeatherApiResponse

class WeatherServiceImpl(
    private val client: HttpClient
) : WeatherService {
        override suspend fun getWeather(city: String): WeatherApiResponse {
            return fetchWeather(
                city = city,
                url = "${ApiConfig.BASE_URL}${ApiConfig.CURRENT_WEATHER_ENDPOINT}"
            )
        }

    private suspend fun fetchWeather(
        city: String,
        url: String,
    ): WeatherApiResponse {
        return try {
            val response = client.get(url) {
                parameter("key", ApiConfig.WEATHER_API_KEY)
                parameter("q", city)
            }.body<WeatherApiResponse>()
            response
        }
        catch (e: Exception) {
            throw Exception("Invalid request for city: $city")
        }
    }



}

