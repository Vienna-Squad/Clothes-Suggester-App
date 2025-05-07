package org.example.data.remote

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.example.data.model.WeatherApiResponse

interface WeatherService {

    suspend fun getWeather(city: String): WeatherApiResponse
    companion object {
        fun create(): WeatherService {
            val client = HttpClient(CIO) {
                install(ContentNegotiation) {
                    json(
                        Json {
                            ignoreUnknownKeys = true
                            isLenient = true
                        }
                            )
                }
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.NONE
                }
            }
            return WeatherServiceImpl(client)
        }
    }
}