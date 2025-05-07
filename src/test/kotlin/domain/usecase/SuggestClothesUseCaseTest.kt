package domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.example.domain.entity.ClothesSuggestion
import org.example.domain.entity.Weather
import org.example.domain.repository.WeatherRepository
import org.example.domain.usecase.SuggestClothesUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class SuggestClothesUseCaseTest {
    private lateinit var suggestClothesUseCase: SuggestClothesUseCase
    private val weatherRepository: WeatherRepository = mockk()

    @BeforeEach
    fun setUp() {
        suggestClothesUseCase = SuggestClothesUseCase(weatherRepository)
    }

    @Test
    fun `should suggest light clothes for hot weather`() = runTest {
        // Given
        val weather = createWeather(temperature = 30.0)
        coEvery { weatherRepository.getWeather("Cairo") } returns weather
        val expected = ClothesSuggestion(
            top = "T-shirt",
            bottom = "Shorts",
            accessories = "Sunglasses"
        )

        // When
        val result = suggestClothesUseCase.invoke("Cairo")

        // Then
        assertEquals(expected, result)
    }

    @Test
    fun `should suggest very light clothes for very hot weather`() = runTest {
        // Given
        val weather = createWeather(temperature = 60.0)
        coEvery { weatherRepository.getWeather("Cairo") } returns weather
        val expected = ClothesSuggestion(
            top = "Shirt",
            bottom = "Shorts",
            accessories = "None"
        )

        // When
        val result = suggestClothesUseCase.invoke("Cairo")

        // Then
        assertEquals(expected, result)
    }

    @Test
    fun `should suggest warm clothes for cold weather`() = runTest {
        // Given
        val weather = createWeather(temperature = 5.0)
        coEvery { weatherRepository.getWeather("Cairo") } returns weather
        val expected = ClothesSuggestion(
            top = "Sweater",
            bottom = "Jeans",
            accessories = "Scarf"
        )

        // When
        val result = suggestClothesUseCase.invoke("Cairo")

        // Then
        assertEquals(expected, result)
    }

    @Test
    fun `should suggest rain gear for rainy weather`() = runTest {
        // Given
        val weather = createWeather(temperature = 20.0)
        coEvery { weatherRepository.getWeather("Cairo") } returns weather
        val expected = ClothesSuggestion(
            top = "Jacket",
            bottom = "Pants",
            accessories = "Umbrella"
        )

        // When
        val result = suggestClothesUseCase.invoke("Cairo")

        // Then
        assertEquals(expected, result)
    }

    @Test
    fun `should suggest heavy clothes for snow weather`() = runTest {
        // Given
        val weather = createWeather(temperature = -10.0)
        coEvery { weatherRepository.getWeather("Cairo") } returns weather
        val expected = ClothesSuggestion(
            top = "Heavy Jacket",
            bottom = "Thermal Pants",
            accessories = "Gloves"
        )

        // When
        val result = suggestClothesUseCase.invoke("Cairo")

        // Then
        assertEquals(expected, result)
    }

    private fun createWeather(
        temperature: Double,
        description: String = " ",
        city: String = "Cairo",
        country: String = "Egypt",
        time: String = "2025-05-06 | 12:00:00"
    ): Weather {
        return Weather(
            city = city,
            country = country,
            time = time,
            temperature = temperature,
            description = description
        )
    }
}