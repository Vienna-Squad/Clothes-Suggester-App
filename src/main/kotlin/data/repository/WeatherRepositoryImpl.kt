package org.example.data.repository

import org.example.data.remote.WeatherService
import org.example.domain.InvalidCityException
import org.example.domain.entity.Weather
import org.example.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherService: WeatherService
):WeatherRepository {
            override suspend fun getWeather(city: String): Result<Weather> {
                return weatherService.getWeather(city).map { response ->
                    val cityName = response.location.name ?: throw InvalidCityException("City name is missing in API response")
                    Weather(
                        city = cityName,
                        country = response.location.country,
                        time = response.location.localtime,
                        temperature = response.current.temp_c,
                        description = response.current.condition.text?:"un known"
                    )
                }
            }


        }


