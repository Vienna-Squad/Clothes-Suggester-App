package org.example.presentation.controller

import org.example.domain.usecase.SuggestClothesUseCase
import org.example.presentation.UiController
import org.koin.java.KoinJavaComponent.getKoin

class SuggestClothesController(
    private val suggestClothesUseCase: SuggestClothesUseCase=getKoin().get()
):UiController {

    override suspend fun execute() {
        println("Enter city name:")
        val input = readln().trim()

        try {
            val result = suggestClothesUseCase.invoke(input)
            result.onSuccess { clotheSuggestion->
                println("top : ${clotheSuggestion.top}")
                println("bottom : ${clotheSuggestion.bottom}")
                println("accessories : ${clotheSuggestion.accessories}")

            }
        }catch (e:Exception){
            println("Error: Failed to get clothing suggestion")

        }

    }
}

