package org.example.presentation.controller

class ExitUIController : UiController {
    override suspend fun execute() {
        println("\n\u001B[34m==========================================\u001B[0m")
        println("\u001B[34m   Thank you for using WeatherWear!       \u001B[0m")
        println("\u001B[34m              Goodbye!                    \u001B[0m")
        println("\u001B[34m==========================================\u001B[0m\n")
    }
}