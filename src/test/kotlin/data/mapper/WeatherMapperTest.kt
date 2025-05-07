package data.mapper

import org.example.data.mapper.WeatherMapper
import org.example.data.model.Condition
import org.example.data.model.CurrentWeather
import org.example.data.model.Location
import org.example.data.model.WeatherApiResponse
import org.example.domain.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class WeatherMapperTest {
    private val weatherMapper = WeatherMapper()

    private val mockResponse = WeatherApiResponse(
        location = Location(
            name = "Cairo",
            region = "Cairo Governorate",
            country = "Egypt",
            latitude = 30.04,
            longitude = 31.24,
            localtime = "2025-05-06 12:00"
        ),
        current = CurrentWeather(
            temp_c = 25.0,
            precipitationMm = 0.0,
            humidity = 65,
            windSpeedKph = 10.0,
            condition = Condition(
                description = "Sunny",
                iconUrl = "//cdn.weatherapi.com/weather/64x64/day/113.png"
            )
        )
    )

    @Test
    fun `mapDtoToWeather should return Weather when response is valid`() {
        // When
        val weather = weatherMapper.mapDtoToWeather(mockResponse)

        // Then
        assertEquals("Cairo", weather.city)
        assertEquals("Egypt", weather.country)
        assertEquals("2025-05-06 12:00", weather.time)
        assertEquals(25.0, weather.temperature)
        assertEquals("Sunny", weather.description)

    }

    @Test
    fun `mapDtoToWeather should throw InvalidCityException when name is null`() {
        // Given
        val invalidResponse = mockResponse.copy(
            location = mockResponse.location.copy(name = null)
        )

        // When & Then
        val thrownException = assertThrows<InvalidCityException> {
            weatherMapper.mapDtoToWeather(invalidResponse)
        }
        assertEquals("City name is missing in API response", thrownException.message)
    }

    @Test
    fun `mapDtoToWeather should throw InvalidCountryException when country is null`() {
        // Given
        val invalidResponse = mockResponse.copy(
            location = mockResponse.location.copy(country = null)
        )

        // When & Then
        val thrownException = assertThrows<InvalidCountryException> {
            weatherMapper.mapDtoToWeather(invalidResponse)
        }
        assertEquals("country name is missing in API response", thrownException.message)
    }
    @Test
    fun `mapDtoToWeather should throw InvalidRegionException when country is null`() {
        // Given
        val invalidResponse = mockResponse.copy(
            location = mockResponse.location.copy(region = null)
        )

        // When & Then
        val thrownException = assertThrows<InvalidRegionException> {
            weatherMapper.mapDtoToWeather(invalidResponse)
        }
        assertEquals("Region name is missing in API response", thrownException.message)
    }

    @Test
    fun `mapDtoToWeather should throw InvalidLocalTimeException when localtime is null`() {
        // Given
        val invalidResponse = mockResponse.copy(
            location = mockResponse.location.copy(localtime = null)
        )

        // When & Then
        val thrownException = assertThrows<InvalidLocalTimeException> {
            weatherMapper.mapDtoToWeather(invalidResponse)
        }
        assertEquals("time is missing in API response", thrownException.message)
    }

    @Test
    fun `mapDtoToWeather should throw InvalidTemperatureException when temperature is null`() {
        // Given
        val invalidResponse = mockResponse.copy(
            current = mockResponse.current.copy(temp_c = null)
        )

        // When & Then
        val thrownException = assertThrows<InvalidTemperatureException> {
            weatherMapper.mapDtoToWeather(invalidResponse)
        }
        assertEquals("temperature is missing in API response", thrownException.message)
    }

    @Test
    fun `mapDtoToWeather should throw InvalidDescriptionException when description is null`() {
        // Given
        val invalidResponse = mockResponse.copy(
            current = mockResponse.current.copy(
                condition = mockResponse.current.condition.copy(description = null)
            )
        )

        // When & Then
        val thrownException = assertThrows<InvalidDescriptionException> {
            weatherMapper.mapDtoToWeather(invalidResponse)
        }
        assertEquals("description is missing in API response", thrownException.message)
    }
}