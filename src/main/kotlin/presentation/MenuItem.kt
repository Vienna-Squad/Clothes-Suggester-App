package org.example.presentation

import org.example.presentation.controller.ExitUIController
import org.example.presentation.controller.GetCurrentWeatherUiController
import org.example.presentation.controller.SuggestClothesController
import org.example.presentation.controller.UiController


enum class MenuItem(val title: String, private val uiController: UiController = ExitUIController() ) {

    GetCurrentWeather("Get Current Weather", uiController=GetCurrentWeatherUiController()),
    SuggestClothes("Clothes Suggestion", uiController=SuggestClothesController()),
    EXIT("Exit");
     suspend fun execute() = this.uiController.execute()
}