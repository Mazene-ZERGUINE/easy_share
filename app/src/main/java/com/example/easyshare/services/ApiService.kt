package com.example.easyshare.services

import com.example.easyshare.models.AllComments
import com.example.easyshare.models.AllProductInfo
import com.example.easyshare.models.ApiResponse
import com.example.easyshare.models.GenericApiResponse
import com.example.easyshare.models.IsStarredPost
import com.example.easyshare.models.LoginRequest
import com.example.easyshare.models.LoginResponse
import com.example.easyshare.models.PublicationFavori
import com.example.easyshare.models.SignupRequest
import com.example.easyshare.models.SignupResponse
import com.example.easyshare.models.UserData
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("publications")
    fun getProducts(): Flowable<AllProductInfo>

    @POST("auth/log-in")
    fun userLogin(
        @Body request: LoginRequest
    ): Flowable<LoginResponse>

    // #region favori
    @GET("favoris/publications/{id}")
    fun isStarredPost(
        @Path("id") id: Int
    ): Observable<GenericApiResponse<IsStarredPost>>

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

    @GET("utilisateurs/{pseudonyme}")
    fun getUserData(
        @Path("pseudonyme") pseudonyme: String
    ): Flowable<UserData>

    @DELETE("utilisateurs/{userId}")
    fun deleteUser(
        @Path("userId") userId: Int
    ): Flowable<Unit>
    // #endregion

    @PUT("utilisateurs/{userId}")
    fun updateUser(
        @Path("userId") userId: Int,
        @Body payload: UserData
    ): Flowable<Unit>
}
