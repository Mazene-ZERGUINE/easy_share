package com.example.easyshare.models

data class LoginRequest(private val email: String, private val mot_de_passe: String)

data class LoginResponse(private val access_token: String) {
    fun getToken(): String {
        return access_token;
    }
}