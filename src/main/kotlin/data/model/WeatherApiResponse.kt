package org.example.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    @SerialName("lat")
    val latitude: Double,
    @SerialName("lon")
    val longitude: Double,
    val localtime: String
)

@Serializable
data class CurrentWeather(
    val temp_c: Double,

    @SerialName("precip_mm")
    val precipitationMm: Double,

    val humidity: Int,

    @SerialName("wind_kph")
    val windSpeedKph: Double,

    val condition: Condition
)

@Serializable
data class Condition(
    @SerialName("text")
    val text: String,

    @SerialName("icon")
    val iconUrl: String
)
