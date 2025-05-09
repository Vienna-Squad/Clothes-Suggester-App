package org.example.presentation

import kotlinx.coroutines.runBlocking
import org.example.presentation.MenuItem.EXIT
import org.example.presentation.MenuItem.entries

class App {
    fun start() {
        println("\n\u001B[34m==========================================\u001B[0m")
        println("\u001B[34m         Welcome to WeatherWear CLI       \u001B[0m")
        println("\u001B[34m==========================================\u001B[0m\n")
        println("Please select an option:\n")
        entries.forEachIndexed { index, option ->
            println("\u001B[32m${index + 1}.\t${option.title}\u001B[0m${if (option == MenuItem.EXIT) " (Closes the app)" else ""}")
        }
        print("\nEnter your choice (1-${MenuItem.entries.size}): ")
        getMenuItemByIndex(readln().toIntOrNull() ?: -1).also { option ->
            runBlocking {
                option.execute()
            }
            if (option == MenuItem.EXIT) return
            start()

        }
    }

    private fun getMenuItemByIndex(input: Int) = entries.getOrNull(input - 1) ?: EXIT

}
