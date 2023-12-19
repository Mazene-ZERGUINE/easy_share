package com.example.easyshare.services

import com.example.easyshare.models.LoginRequest
import com.example.easyshare.models.LoginResponse
import com.example.easyshare.models.ProductData
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("products")
    fun getFakeProducts(): Flowable<List<ProductData>>

    @POST("auth/log-in")
    fun userLogin(
        @Body request: LoginRequest
    ): Flowable<LoginResponse>
}
