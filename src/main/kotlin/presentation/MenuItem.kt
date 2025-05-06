package org.example.presentation

import org.example.presentation.controller.GetCurrentWeatherUiController
import org.example.presentation.controller.SuggestClothesController


enum class MenuItem(val title: String, private val uiController: UiController ) {

    GetCurrentWeather("Get Current Weather", uiController=GetCurrentWeatherUiController()),
    SuggestClothes("Clothes Suggestion", uiController=SuggestClothesController());

     suspend fun execute() = this.uiController.execute()
}