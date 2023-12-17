package com.example.easyshare.utilis
object TokenManager {
    private var userToken: String? = null

    fun setToken(token: String) {
        userToken = token
    }

    fun getToken(): String? {
        return userToken
    }

    fun clearToken() {
        userToken = null
    }
}
