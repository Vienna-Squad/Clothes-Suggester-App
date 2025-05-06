package org.example

import kotlinx.coroutines.runBlocking
import org.example.di.appModule
import org.example.di.useCaseModule
import org.example.presentation.App
import org.koin.core.context.GlobalContext.startKoin


 fun main() {
    runBlocking {
        startKoin { modules(appModule, useCaseModule) }
        App().start()
    }
}