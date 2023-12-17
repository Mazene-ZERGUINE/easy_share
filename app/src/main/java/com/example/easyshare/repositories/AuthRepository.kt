package com.example.easyshare.repositories

import com.example.easyshare.models.LoginRequest
import com.example.easyshare.models.LoginResponse
import com.example.easyshare.services.ApiService
import io.reactivex.rxjava3.core.Flowable

class AuthRepository(private val apiService: ApiService) {

    fun login(payload: LoginRequest): Flowable<LoginResponse> {
        return apiService.userLogin(payload)
    }
}