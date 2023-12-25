package com.example.easyshare.di.modules

import LoginViewModel
import com.example.easyshare.repositories.AuthRepository
import com.example.easyshare.repositories.ProductsRepository
import com.example.easyshare.repositories.UserRepository
import com.example.easyshare.viewmodel.FavoritePostViewModel
import com.example.easyshare.viewmodel.ProductViewModel
import com.example.easyshare.viewmodel.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val coreModule =
    module {
        single { ProductViewModel(get()) }

        single { ProductsRepository(get()) }

        viewModel { LoginViewModel(get()) }

        single { AuthRepository(get()) }

        viewModel { SignupViewModel(get()) }

        single { UserRepository(get()) }
        viewModel { FavoritePostViewModel(get()) }
    }
