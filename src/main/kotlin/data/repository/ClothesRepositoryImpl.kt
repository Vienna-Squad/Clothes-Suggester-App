package org.example.data.repository

import dev.shreyaspatil.ai.client.generativeai.type.content
import org.example.data.remote.Gemini
import org.example.domain.entity.ClothesSuggestion
import org.example.domain.repository.ClothesRepository

class ClothesRepositoryImpl(
    private val geminiModel: Gemini
) : ClothesRepository {
    override suspend fun getClothingSuggestionByTemperature(weatherTemperature: Double): ClothesSuggestion {
        val model = geminiModel.createClient()
        val result = model.generateContent(content {
            val temperature = preprocessWeatherTemperature(weatherTemperature.toString())
            text(temperature)
        }).text

        val clothesList = result?.filter { char -> char != '[' && char != ']' }
            ?.split(",") ?: throw Exception("error in parsing result")

        return ClothesSuggestion(
            top = clothesList.first(),
            bottom = clothesList[1],
            accessories = clothesList[2]
        )

    }

    private fun preprocessWeatherTemperature(weatherTemperature: String): String {
        val prompt = """
        You are a helpful assistant that suggest clothes by weather , only return Action
        Here are some examples of commands and how to interpret them:

        Command: Weather Temperature degree 
        Action: [topBody,underBody,accessories] 

        Command: $weatherTemperature
        Action: 
    """.trimIndent()
        return prompt
    }
}