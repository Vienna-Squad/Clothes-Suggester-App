package org.example.presentation

import org.example.domain.usecase.SuggestClothesUseCase
class SuggestClothesController(
    private val suggestClothesUseCase: SuggestClothesUseCase
) {

    suspend fun run() {
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

