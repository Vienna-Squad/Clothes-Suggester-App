package domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.example.domain.entity.Weather
import org.example.domain.repository.WeatherRepository
import org.example.domain.usecase.GetCurrentWeatherUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GetCurrentWeatherUseCaseTest{

    lateinit var weatherRepository:WeatherRepository
    lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase

    val dummyWeather=Weather(
        city = "Cairo",
        country = "Egypt",
        time = "2025-05-06T12:00:00Z",
        temperature = 30.0,
        description = "Sunny"
    )

    @BeforeEach
    fun setUp(){
        weatherRepository=mockk()
        getCurrentWeatherUseCase= GetCurrentWeatherUseCase(weatherRepository)

    }


    @Test
    fun `should return weather when entering a valid city`() {
        runTest{
            // given
            val city = "cairo"
            coEvery { weatherRepository.getWeather(city) } returns Result.success(dummyWeather)

            //when
            val actualResult = getCurrentWeatherUseCase(city)

            //then
            coVerify { weatherRepository.getWeather(city) }
            assertEquals(dummyWeather, actualResult.getOrNull())

        }
    }

    @Test
    fun `should return exception when a not valid city`() {
        runTest {
            // given
            val city = "invalid city"
            val exception=Exception("Invalid city name")
            coEvery { weatherRepository.getWeather(city) } returns Result.failure(exception)

            //when
            val actualResult = getCurrentWeatherUseCase(city)

            //then
            assertEquals(exception.message,actualResult.exceptionOrNull()?.message)

        }
    }

    @Test
    fun `should return exception when repository fails`() {
        runTest {
            // given
            val city = "cairo"
            val exception=Exception("Repository failed to fetch data")
            coEvery { weatherRepository.getWeather(city) } returns Result.failure(exception)

            //when
            val actualResult = getCurrentWeatherUseCase(city)

            //then
            coVerify { weatherRepository.getWeather(city) }
            assertEquals(exception.message,actualResult.exceptionOrNull()?.message)

        }
    }


}