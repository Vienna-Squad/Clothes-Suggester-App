package org.example.presentation.controller

import org.example.domain.usecase.GetCurrentWeatherUseCase
import org.example.presentation.UiController


class GetCurrentWeatherUiController(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : UiController {

    override suspend fun execute() {

        println("Enter your city: ")
        val city = readln().trim()
        try {
            val weatherResult = getCurrentWeatherUseCase(city)
            println("\n\u001B[34m==========================================\u001B[0m")
            println("\u001B[34m           Weather Information            \u001B[0m")
            println("\u001B[34m==========================================\u001B[0m")
            println("City : ${weatherResult.city}")
            println("Country : ${weatherResult.country}")
            println("Temperature : ${weatherResult.temperature}°C")
            println("Description : ${weatherResult.description}")

        } catch (e: Exception) {
            println("Error : invalid city")
        }


    }


}