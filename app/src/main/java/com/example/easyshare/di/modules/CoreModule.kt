package com.example.easyshare.di.modules

import LoginViewModel
import com.example.easyshare.repositories.AuthRepository
import com.example.easyshare.repositories.ProductsRepository
import com.example.easyshare.viewmodel.CommentViewModel
import com.example.easyshare.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val coreModule =
    module {
        single { ProductsRepository(get()) }

        single { ProductViewModel(get()) }

        single { LoginViewModel(get()) }

        single { AuthRepository(get()) }

        viewModel { CommentViewModel(get()) }
    }
