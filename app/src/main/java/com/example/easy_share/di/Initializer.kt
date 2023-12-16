package com.example.easy_share.di

import android.content.Context
import com.example.easy_share.di.modules.coreModule
import com.example.easy_share.di.modules.remoteModule
import de.hdodenhof.circleimageview.BuildConfig
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