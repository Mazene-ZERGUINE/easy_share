package com.example.easyshare.repositories

import com.example.easyshare.models.ApiResponse
import com.example.easyshare.models.PublicationFavori
import com.example.easyshare.services.ApiService
import com.example.easyshare.utilis.TokenManager
import io.reactivex.rxjava3.core.Flowable

class UserRepository(private val apiService: ApiService) {
    fun getFavoritePosts(): Flowable<ApiResponse<PublicationFavori>> {
        val pseudonyme = TokenManager.getPseudonymeFromToken()

        return apiService.getFavoritePosts(pseudonyme)
    }
}
