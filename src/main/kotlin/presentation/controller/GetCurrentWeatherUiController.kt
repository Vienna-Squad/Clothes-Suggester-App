package org.example.presentation.controller

import org.example.domain.usecase.GetCurrentWeatherUseCase
import org.koin.java.KoinJavaComponent.getKoin


class GetCurrentWeatherUiController(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase = getKoin().get(),
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
            println("Region : ${weatherResult.region}")
            println("Country : ${weatherResult.country}")
            println("Temperature : ${weatherResult.temperature}°C")
            println("Description : ${weatherResult.description}")

        } catch (e: Exception) {
            println("Error : invalid city")
        }


    }

}