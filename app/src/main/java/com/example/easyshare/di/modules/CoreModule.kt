package com.example.easyshare.di.modules

import LoginViewModel
import com.example.easyshare.repositories.AuthRepository
import com.example.easyshare.repositories.ProductsRepository
<<<<<<< HEAD
import com.example.easyshare.repositories.UserRepository
import com.example.easyshare.ui.viewmodel.AccountViewModel
import com.example.easyshare.ui.viewmodel.ProductViewModel
import com.example.easyshare.ui.viewmodel.SignupViewModel
import com.example.easyshare.viewmodel.FavoritePostViewModel
=======
import com.example.easyshare.viewmodel.CommentViewModel
import com.example.easyshare.viewmodel.ProductViewModel
>>>>>>> a53e9f8a48296bf4a0deda3fe8f0d51ebc5d5f74
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val coreModule =
    module {
        single { ProductViewModel(get(), get()) }

        single { ProductsRepository(get()) }

        viewModel { LoginViewModel(get()) }

        single { AuthRepository(get()) }

        viewModel { SignupViewModel(get()) }

        single { UserRepository(get()) }

        viewModel { FavoritePostViewModel(get()) }

        viewModel { AccountViewModel(get()) }
    }
