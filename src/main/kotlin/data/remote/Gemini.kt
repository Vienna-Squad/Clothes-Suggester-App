package org.example.data.remote

import dev.shreyaspatil.ai.client.generativeai.GenerativeModel

object Gemini {
    fun createClient(): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-2.0-flash",
            apiKey = "AIzaSyAB4INndt4sGxXGSqd064mFb2KcMHEk3_o"
        )
    }

}