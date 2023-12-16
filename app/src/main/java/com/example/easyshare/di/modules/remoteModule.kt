package com.example.easyshare.di.modules

import com.example.easyshare.di.FakeJsonConf
import com.example.easyshare.dummy.FakeProductService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val fakeProductData = "fakeProductData"

internal val remoteModule = module {
    single(named(fakeProductData)) { createRetrofitClient(get(), get<FakeJsonConf>().baseUrl) }

    single { createOkHttpClient() }

    single {
        createWebService<FakeProductService>(
            get(named(fakeProductData))
        )
    }
}

inline fun <reified T> createWebService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()
}


fun createRetrofitClient(okhttpClient: OkHttpClient, baseUrl: String): Retrofit {
    val gsonConverter =
        GsonConverterFactory.create(
            GsonBuilder().create()
        )

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okhttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(gsonConverter)
        .build()
}