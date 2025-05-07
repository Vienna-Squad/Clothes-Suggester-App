package data.repository

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.example.data.mapper.WeatherMapper
import org.example.data.model.Condition
import org.example.data.model.CurrentWeather
import org.example.data.model.Location
import org.example.data.model.WeatherApiResponse
import org.example.data.remote.WeatherService
import org.example.data.repository.WeatherRepositoryImpl
import org.example.domain.InvalidCityException
import org.example.domain.entity.Weather
import org.example.domain.repository.WeatherRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class WeatherRepositoryImplTest {
    private lateinit var weatherService: WeatherService
    private lateinit var repository: WeatherRepository
    private lateinit var weatherMapper: WeatherMapper

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
    private val mockWeather = Weather(
        city = "Cairo",
        country = "Egypt",
        time = "2025-05-06 12:00",
        temperature = 25.0,
        description = "Sunny"
    )

    @BeforeEach
    fun setup() {
        weatherService = mockk(relaxed = true)
        weatherMapper=mockk(relaxed = true)
        repository = WeatherRepositoryImpl(weatherService,weatherMapper)
    }


    @Test
    fun `getWeather should return Weather when API call is successful`() = runTest {
        // Given
        coEvery { weatherService.getWeather("Cairo") } returns mockResponse
        coEvery { weatherMapper.mapDtoToWeather(mockResponse) } returns mockWeather

        // When
        val weather = repository.getWeather("Cairo")

        // Then
        assertEquals("Cairo", weather.city)
        assertEquals("Egypt", weather.country)
        assertEquals(25.0, weather.temperature)
    }




    @Test
    fun `getWeather should throw exception when API call fails`() = runTest {
        // Given
        val exception = RuntimeException("API Error")
        coEvery { weatherService.getWeather("Cairo") } throws exception

        // When & Then
        val thrownException = assertThrows<RuntimeException> {
            repository.getWeather("Cairo")
        }
        assertEquals("API Error", thrownException.message)
    }




    @Test
    fun `getWeather should throw InvalidCityException when city is not found`() = runTest {
        // Given
        coEvery { weatherService.getWeather("InvalidCity") } throws InvalidCityException("City 'InvalidCity' not found")

        // When & Then
        val thrownException = assertThrows<InvalidCityException> {
            repository.getWeather("InvalidCity")
        }
        assertEquals("City 'InvalidCity' not found", thrownException.message)
    }


}