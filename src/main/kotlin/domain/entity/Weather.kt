package org.example.domain.entity

data class Weather(
    val city: String,
    val region: String,
    val country: String,
    val time: String,
    val temperature: Double,
    val description: String,

    )