package org.example.presentation

import org.example.presentation.MenuItem.entries

class App {
      suspend fun start() {
        MenuItem.entries.forEachIndexed { index, option -> print("\n${index + 1}.\t${option.title}") }
        print("\nEnter your choice (1-${MenuItem.entries.size}): ")
        getMenuItemByIndex(readln().toIntOrNull() ?: -1).also { option ->
            option?.execute()
            start()
        }
    }
    private fun getMenuItemByIndex(input: Int) = entries.getOrNull(input - 1)
}
