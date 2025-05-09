package org.example.data.mapper

import org.example.data.model.WeatherApiResponse
import org.example.domain.*
import org.example.domain.entity.Weather

class WeatherMapper {
    fun mapDtoToWeather(weatherApiResponse: WeatherApiResponse): Weather {
        return Weather(
            city = weatherApiResponse.location.name
                ?: throw InvalidCityException("City name is missing in API response"),
            region = weatherApiResponse.location.region
                ?: throw InvalidRegionException("Region name is missing in API response"),
            country = weatherApiResponse.location.country
                ?: throw InvalidCountryException("country name is missing in API response"),
            time = weatherApiResponse.location.localtime
                ?: throw InvalidLocalTimeException("time is missing in API response"),
            temperature = weatherApiResponse.current.temp_c
                ?: throw InvalidTemperatureException("temperature is missing in API response"),
            description = weatherApiResponse.current.condition.description
                ?: throw InvalidDescriptionException("description is missing in API response")
        )
    }


}