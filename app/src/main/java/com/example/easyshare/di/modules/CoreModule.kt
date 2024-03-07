package com.example.easyshare.di.modules

import LoginViewModel
import android.content.Context
import com.example.easyshare.repositories.AuthRepository
import com.example.easyshare.repositories.ProductsRepository
import com.example.easyshare.repositories.UserRepository
import com.example.easyshare.ui.viewmodel.AccountViewModel
import com.example.easyshare.ui.viewmodel.CommentViewModel
import com.example.easyshare.ui.viewmodel.ProductViewModel
import com.example.easyshare.ui.viewmodel.SignupViewModel
import com.example.easyshare.viewmodel.FavoritePostViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val coreModule =
    module {
        viewModel { (context: Context) ->
            ProductViewModel(get(), get(), context)
        }

        single { ProductsRepository(get()) }

        viewModel { LoginViewModel(get()) }

        single { AuthRepository(get()) }

        viewModel { SignupViewModel(get()) }

        single { UserRepository(get()) }

        viewModel { FavoritePostViewModel(get()) }

        viewModel { AccountViewModel(get()) }

        viewModel { CommentViewModel(get()) }
    }
