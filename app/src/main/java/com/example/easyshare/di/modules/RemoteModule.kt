package com.example.easyshare.di.modules

import android.content.Context
import com.example.easyshare.di.FakeJsonConf
import com.example.easyshare.services.ApiService
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_SERVICE = "apiService"

internal val remoteModule =
    module {
        single(named(API_SERVICE)) { createRetrofitClient(get(), get<FakeJsonConf>().baseUrl) }

        single { createOkHttpClient(androidContext()) }

        single {
            createWebService<ApiService>(
                get(named(API_SERVICE))
            )
        }
    }

fun createAuthorizationInterceptor(context: Context): Interceptor {
    return Interceptor { chain ->
        val sharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("ACCESS_TOKEN", null)

        val requestOriginal = chain.request()
        val requestModifie =
            if (token != null) {
                requestOriginal.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .build()
            } else {
                requestOriginal
            }

        chain.proceed(requestModifie)
    }
}

inline fun <reified T> createWebService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}

fun createOkHttpClient(context: Context): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(createAuthorizationInterceptor(context))
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()
}

fun createRetrofitClient(
    okhttpClient: OkHttpClient,
    baseUrl: String
): Retrofit {
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
