package com.example.easyshare.services

import com.example.easyshare.models.AllProductInfo
import com.example.easyshare.models.ApiResponse
import com.example.easyshare.models.CommentRequest
import com.example.easyshare.models.CommentResponse
import com.example.easyshare.models.GenericApiResponse
import com.example.easyshare.models.ImageData
import com.example.easyshare.models.IsStarredPost
import com.example.easyshare.models.LoginRequest
import com.example.easyshare.models.LoginResponse
import com.example.easyshare.models.NewProductRequest
import com.example.easyshare.models.PublicationFavori
import com.example.easyshare.models.SignupRequest
import com.example.easyshare.models.SignupResponse
import com.example.easyshare.models.UserData
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @GET("publications")
    fun getProducts(): Single<AllProductInfo>

    @Multipart
    @POST("publications")
    fun addNewProduct(
        @Part("titre") titre: RequestBody,
        @Part("description") description: RequestBody,
        @Part image: MultipartBody.Part,
        @Part("utilisateur_id") utilisateur_id: RequestBody,
    ): Flowable<Unit>



    @POST("auth/log-in")
    fun userLogin(
        @Body request: LoginRequest
    ): Flowable<LoginResponse>

    // #region favori
    @GET("favoris/publications/{id}")
    fun isStarredPost(
        @Path("id") id: Int
    ): Observable<GenericApiResponse<IsStarredPost>>

    @GET("commentaires/publications/{publicationId}")
    fun getAllProductsComments(
        @Path("publicationId") publicationId: Int
    ): Flowable<CommentResponse>

    @POST("commentaires/publications/{publicationId}")
    fun addCommentToProduct(
        @Path("publicationId") publicationId: Int,
        @Body request: CommentRequest
    ): Flowable<Unit>

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

    @GET("images")
    fun getImage(
    ): Flowable<List<ImageData>>
}
