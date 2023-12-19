package com.example.easyshare.di.modules

import LoginViewModel
import com.example.easyshare.repositories.AuthRepository
import com.example.easyshare.repositories.FakeProductRepository
import com.example.easyshare.viewmodel.ProductViewModel
import org.koin.dsl.module

internal val coreModule =
    module {
        single { ProductViewModel(get()) }

        single { FakeProductRepository(get()) }

        single { LoginViewModel(get()) }

        single { AuthRepository(get()) }
    }
