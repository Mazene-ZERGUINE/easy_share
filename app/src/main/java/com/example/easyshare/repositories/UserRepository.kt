package com.example.easyshare.repositories

import com.example.easyshare.models.ApiResponse
import com.example.easyshare.models.GenericApiResponse
import com.example.easyshare.models.IsStarredPost
import com.example.easyshare.models.PublicationFavori
import com.example.easyshare.models.UserData
import com.example.easyshare.services.ApiService
import com.example.easyshare.utilis.TokenManager
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

class UserRepository(private val apiService: ApiService) {
    fun getFavoritePosts(): Flowable<ApiResponse<PublicationFavori>> {
        val pseudonyme = TokenManager.getPseudonymeFromToken()

        return apiService.getFavoritePosts(pseudonyme)
    }

    fun isStarredPost(id: Int): Observable<GenericApiResponse<IsStarredPost>> {
        return apiService.isStarredPost(id)
    }

    fun starPost(id: Int): Flowable<Unit> {
        return apiService.starPost(id)
    }

    fun unstarPost(id: Int): Flowable<Unit> {
        return apiService.unstarPost(id)
    }

    fun getUserData(userName: String): Flowable<UserData> {
        return apiService.getUserData(userName)
    }

    fun deleteUser(userId: Int): Flowable<Unit> {
        return apiService.deleteUser(userId)
    }
}
