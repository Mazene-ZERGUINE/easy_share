package com.example.easy_share.di.modules

import com.example.easy_share.dummy.FakeProductService
import com.example.easy_share.repositories.FakeProductRepository
import com.example.easy_share.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val coreModule = module {
    single { ProductViewModel(get()) }

    single { FakeProductRepository(get()) }
}