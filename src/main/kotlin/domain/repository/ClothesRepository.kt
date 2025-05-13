package org.example.domain.repository

import org.example.domain.entity.ClothesSuggestion

interface ClothesRepository {
    suspend fun getClothingSuggestionByTemperature(weatherTemperature: Double): ClothesSuggestion
}