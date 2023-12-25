package com.example.easyshare.services

import com.example.easyshare.models.AllProductInfo
import com.example.easyshare.models.ApiResponse
import com.example.easyshare.models.LoginRequest
import com.example.easyshare.models.LoginResponse
import com.example.easyshare.models.PublicationFavori
import com.example.easyshare.models.SignupRequest
import com.example.easyshare.models.SignupResponse
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("publications")
    fun getProducts(): Single<AllProductInfo>

    @POST("auth/log-in")
    fun userLogin(
        @Body request: LoginRequest
    ): Flowable<LoginResponse>

    // #region favori
    @POST("favoris/publications/{id}")
    fun starPost(
        @Path("id") id: Int
    ): Flowable<Unit>

    @DELETE("favoris/publications/{id}")
    fun unstarPost(
        @Path("id") id: Int
    ): Flowable<Unit>
    // #endregion

    // #region utilisateur
    @GET("utilisateurs/{pseudonyme}/favoris")
    fun getFavoritePosts(
        @Path("pseudonyme") pseudonyme: String
    ): Flowable<ApiResponse<PublicationFavori>>

    @POST("utilisateurs/signup/confirmation")
    fun signup(
        @Body request: SignupRequest
    ): Flowable<SignupResponse>
    // #endregion
}
