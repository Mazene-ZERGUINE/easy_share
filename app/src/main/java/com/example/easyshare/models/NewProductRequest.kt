package com.example.easyshare.models

import okhttp3.MultipartBody
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class NewProductRequest(
    private val titre: String,
    private val description: String,
    private val utilisateur_id: Int,
    private val image: MultipartBody.Part? = null,
    private val statut: String = "actif",
    private val categorie_id: Int = 1,
    private val created_at: String =
        LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        )
)
