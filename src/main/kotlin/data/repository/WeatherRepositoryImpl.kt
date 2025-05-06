package org.example.data.repository

import org.example.data.mapper.WeatherMapper
import org.example.data.remote.WeatherService
import org.example.domain.entity.Weather
import org.example.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherService: WeatherService,
    private val weatherMapper: WeatherMapper
) : WeatherRepository {
    override suspend fun getWeather(city: String): Weather {
        val res = weatherService.getWeather(city)

        return weatherMapper.mapDtoToWeather(res)
    }
}


