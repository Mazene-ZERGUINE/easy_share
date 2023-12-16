package com.example.easyshare.di

import android.content.Context
import com.example.easyshare.di.modules.coreModule
import com.example.easyshare.di.modules.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.error.KoinAppAlreadyStartedException
import org.koin.dsl.module



fun injectModuleDependencies(context: Context) {
    try {
        startKoin {
            androidContext(context)
            modules(modules)
        }
    }catch (alreadyStart: KoinAppAlreadyStartedException) {
        loadKoinModules(modules)
    }
}


fun parseAndInjectConfiguration() {
    val apiConf = FakeJsonConf(baseUrl = "https://fakerapi.it/api/v1/")
    modules.add(
        module {
            single { apiConf }
        }
    )
}

private val modules = mutableListOf(coreModule, remoteModule)

data class FakeJsonConf(val baseUrl: String)