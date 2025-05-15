package org.example.data.remote

import dev.shreyaspatil.ai.client.generativeai.GenerativeModel

object Gemini {
    fun createClient(): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-2.0-flash",
            apiKey = ApiConfig.GEMINI_API_KEY
        )
    }

}