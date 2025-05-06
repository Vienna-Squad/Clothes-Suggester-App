package org.example.domain.usecase

import org.example.domain.repository.WeatherRepository

class GetCurrentWeatherUseCase (
    private val weatherRepository:WeatherRepository
){
}