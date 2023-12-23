package com.example.easyshare.services

import com.example.easyshare.models.AllProductInfo
import com.example.easyshare.models.LoginRequest
import com.example.easyshare.models.LoginResponse
import com.example.easyshare.models.SignupRequest
import com.example.easyshare.models.SignupResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("publications")
    fun getProducts(): Single<AllProductInfo>

    @POST("auth/log-in")
    fun userLogin(
        @Body request: LoginRequest
    ): Flowable<LoginResponse>

    @POST("utilisateurs/signup/confirmation")
    fun signup(
        @Body request: SignupRequest
    ): Flowable<SignupResponse>
}
