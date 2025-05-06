package org.example.presentation.controller

import org.example.domain.usecase.SuggestClothesUseCase
import org.example.presentation.UiController

class SuggestClothesController(
    private val suggestClothesUseCase: SuggestClothesUseCase
):UiController {

    override suspend fun execute() {
        println("Enter city name:")
        val input = readLine()?.trim().orEmpty()

        if (input.isBlank()) {
            println("City name cannot be empty")
        }


        val result = suggestClothesUseCase.invoke(input)

        result.fold(
            onSuccess = { suggestion ->
                println("Clothing suggestion for $input:")
                println("Top: ${suggestion.top}")
                println("Bottom: ${suggestion.bottom}")
                println("Accessories: ${suggestion.accessories}")
            },
            onFailure = { exception ->
                println("Error: Failed to get clothing suggestion - ${exception.message}")
            }
        )
    }
}

