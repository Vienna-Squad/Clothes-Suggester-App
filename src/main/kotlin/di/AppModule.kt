package org.example.di

import org.example.data.mapper.WeatherMapper
import org.example.data.remote.Gemini
import org.example.data.remote.WeatherService
import org.example.data.repository.ClothesRepositoryImpl
import org.example.data.repository.WeatherRepositoryImpl
import org.example.domain.repository.ClothesRepository
import org.example.domain.repository.WeatherRepository
import org.koin.dsl.module

val appModule = module {

    single {WeatherService.create()}
    single { WeatherMapper() }
    single { Gemini }
    single<WeatherRepository> {WeatherRepositoryImpl(get(),get()) }
    single<ClothesRepository> { ClothesRepositoryImpl(get()) }
}