package org.example.presentation.controller

import org.example.domain.usecase.GetCurrentWeatherUseCase
import org.example.presentation.UiController


class GetCurrentWeatherUiController(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : UiController {

    override suspend fun execute() {

        println("Enter your city: ")
        val city = readln()

        if (city.isBlank() || !city.all { it.isLetter() }) {
            println("Invalid city name, please enter a valid city.")
        }

        val weatherResult = getCurrentWeatherUseCase(city)

        weatherResult.onSuccess { weather ->
            println(weather)
        }.onFailure { exception ->
            println("Failed to get weather: ${exception.message}")
        }
    }


}