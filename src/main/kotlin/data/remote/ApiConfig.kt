package org.example.data.remote

import java.io.FileInputStream
import java.util.*

object ApiConfig {
    const val BASE_URL = "http://api.weatherapi.com/v1"
    const val CURRENT_WEATHER_ENDPOINT = "/current.json"
    val API_KEY: String by lazy {
        val properties = Properties()
        properties.load(FileInputStream("local.properties"))
        properties.getProperty("api.key") ?: throw IllegalStateException("API key not found in local.properties")
    }
}