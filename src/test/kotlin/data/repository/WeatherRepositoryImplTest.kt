package data.repository

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.example.data.model.Condition
import org.example.data.model.CurrentWeather
import org.example.data.model.Location
import org.example.data.model.WeatherApiResponse
import org.example.data.remote.WeatherService
import org.example.data.repository.WeatherRepositoryImpl
import org.example.domain.CityNotFoundException
import org.example.domain.repository.WeatherRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class WeatherRepositoryImplTest {
    private lateinit var weatherService: WeatherService
    private lateinit var repository: WeatherRepository

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

    @BeforeEach
    fun setup() {
        weatherService = mockk(relaxed = true)
        repository = WeatherRepositoryImpl(weatherService)
    }

    @Test
    fun `getWeather should return Weather when API call is successful`() = runTest {
        // Given
        coEvery { weatherService.getWeather("Cairo") } returns Result.success(mockResponse)

        // Then
        val result = repository.getWeather("Cairo")

        // When
        assertTrue(result.isSuccess)
        val weather = result.getOrNull()
        assertEquals("Cairo", weather?.city)
        assertEquals("Egypt", weather?.country)
        assertEquals(25.0, weather?.temperature)
        assertEquals("Sunny", weather?.description)
    }

    @Test
    fun `getWeather should return failure when API call fails`() = runTest {
        // Given
        val exception = RuntimeException("API Error")
        coEvery { weatherService.getWeather("Cairo") } returns Result.failure(exception)

        // when
        val result = repository.getWeather("Cairo")

        // Then
        assertTrue(result.isFailure)
    }

    @Test
    fun `getWeather should return failure when city is not found`() = runTest {
        //Given
        coEvery { weatherService.getWeather("InvalidCity") } returns Result.failure(CityNotFoundException("City 'InvalidCity' not found"))

        //When
        val result = repository.getWeather("InvalidCity")

        // Then
        assertTrue(result.isFailure)
        val exception = result.exceptionOrNull()
        assertEquals("City 'InvalidCity' not found", exception?.message)
    }


}