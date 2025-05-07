package org.example.domain.repository

import org.example.domain.entity.Weather

interface WeatherRepository {
    suspend fun getWeather(city:String):Weather

}