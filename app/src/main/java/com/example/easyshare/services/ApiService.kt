package com.example.easyshare.services

import com.example.easyshare.models.AllProductInfo
import com.example.easyshare.models.CommentResponse
import com.example.easyshare.models.LoginRequest
import com.example.easyshare.models.LoginResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("publications")
    fun getProducts(): Flowable<AllProductInfo>

    @POST("auth/log-in")
    fun userLogin(
        @Body request: LoginRequest
    ): Flowable<LoginResponse>

    @GET("commentaires/publications/{publicationId}")
    fun getAllProductsComments(
        @Path("publicationId") publicationId: Int
    ): Flowable<CommentResponse>
}
