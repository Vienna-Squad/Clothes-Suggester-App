package org.example.presentation.controller

import org.example.domain.usecase.GetCurrentWeatherUseCase
import org.example.presentation.UiController
import org.koin.java.KoinJavaComponent.getKoin


class GetCurrentWeatherUiController(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase = getKoin().get(),
) : UiController {

    override suspend fun execute() {

        println("Enter your city: ")
        val city = readln().trim()

        try {
            val weatherResult = getCurrentWeatherUseCase(city)
            weatherResult.onSuccess { weather ->
                println("Weather at : ${weather.city}")
                println("Country : ${weather.country}")
                println("Temperature : ${weather.temperature}")
                println("Description : ${weather.description}")

            }
        } catch (e: Exception) {
            println("Invalid city name, please enter a valid city.")
        }


    }


}