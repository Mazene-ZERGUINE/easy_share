package com.example.easyshare.utilis

import android.content.Context

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

    fun storeAccessToken(
        context: Context,
        token: String
    ) {
        val sharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("ACCESS_TOKEN", token).apply()
    }
}
