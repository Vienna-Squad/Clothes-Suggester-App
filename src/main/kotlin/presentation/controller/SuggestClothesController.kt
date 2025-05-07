package org.example.presentation.controller

import org.example.domain.usecase.SuggestClothesUseCase
import org.example.presentation.UiController
import org.koin.java.KoinJavaComponent

class SuggestClothesController(
    private val suggestClothesUseCase: SuggestClothesUseCase = getKoin().get()
) : UiController {

    override suspend fun execute() {
        println("Enter city name:")
        val input = readln().trim()

        try {
            val result = suggestClothesUseCase(input)
            println("\n\u001B[34m==========================================\u001B[0m")
            println("\u001B[34m        Clothing Recommendation           \u001B[0m")
            println("\u001B[34m==========================================\u001B[0m")
            println("top : ${result.top}")
            println("bottom : ${result.bottom}")
            println("accessories : ${result.accessories}")

        } catch (e: Exception) {
            println("Error : invalid city")
        }

    }

}

