package org.example.di

import org.example.domain.usecase.GetCurrentWeatherUseCase
import org.example.domain.usecase.SuggestClothesUseCase
import org.koin.dsl.module


val useCaseModule= module {
    single { GetCurrentWeatherUseCase(get()) }
    single {SuggestClothesUseCase(get()) }

}