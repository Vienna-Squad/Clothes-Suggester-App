package org.example


import org.example.di.appModule
import org.example.di.useCaseModule
import org.example.presentation.App
import org.koin.core.context.GlobalContext.startKoin


fun main() {

    startKoin { modules(appModule, useCaseModule) }
    App().start()

}