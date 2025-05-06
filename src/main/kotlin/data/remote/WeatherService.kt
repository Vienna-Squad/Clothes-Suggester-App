package org.example.data.remote

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.gson.*
import org.example.data.model.WeatherApiResponse

interface WeatherService {

    suspend fun getWeather(city: String): Result<WeatherApiResponse>
    companion object {
        fun create(): WeatherService {
            val client = HttpClient(CIO) {
                install(ContentNegotiation) {
                  gson ()
                }
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
            }
            return WeatherServiceImpl(client)
        }
    }
}