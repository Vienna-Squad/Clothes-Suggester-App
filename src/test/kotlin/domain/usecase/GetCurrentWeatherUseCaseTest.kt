package domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.example.domain.entity.Weather
import org.example.domain.repository.WeatherRepository
import org.example.domain.usecase.GetCurrentWeatherUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class GetCurrentWeatherUseCaseTest {

    lateinit var weatherRepository: WeatherRepository
    lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase

    val dummyWeather = Weather(
        city = "Cairo",
        region = "Al Qahirah",
        country = "Egypt",
        time = "2025-05-06T12:00:00Z",
        temperature = 30.0,
        description = "Sunny"
    )

    @BeforeEach
    fun setUp() {
        weatherRepository = mockk()
        getCurrentWeatherUseCase = GetCurrentWeatherUseCase(weatherRepository)

    }


    @Test
    fun `should return weather when entering a valid city`() {
        runTest {
            // given
            val city = "cairo"
            coEvery { weatherRepository.getWeather(city) } returns dummyWeather

            //when
            val actualResult = getCurrentWeatherUseCase(city)

            //then
            coVerify { weatherRepository.getWeather(city) }
            assertEquals(dummyWeather, actualResult)

        }
    }

    @Test
    fun `should return exception when a not valid city`() {
        runTest {
            // given
            val city = "invalid city"
            val exception = Exception("Invalid city name")
            coEvery { weatherRepository.getWeather(city) } throws exception

            // when & then
            val thrownException = assertThrows<Exception> {
                getCurrentWeatherUseCase(city)
            }
            assertEquals("Invalid city name", thrownException.message)

        }
    }



}