package org.example.data.model

import kotlinx.serialization.Serializable

class WeatherApiResponse {
    @Serializable
    data class WeatherApiResponse(
        val location: Location,
        val current: CurrentWeather
    )

    @Serializable
    data class Location(
        val name: String,
        val region: String,
        val country: String,
        val lat: Double,
        val lon: Double,
        val localtime:String
    )

    @Serializable
    data class CurrentWeather(
        val temp_c: Double,
        val precip_mm: Double,
        val humidity: Int,
        val wind_kph: Double,
        val condition: Condition
    )

    @Serializable
    data class Condition(
        val text: String,
        val icon: String
    )
}