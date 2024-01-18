package com.example.easyshare.models

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class NewProductRequest(
    private val titre: String,
    private val description: String,
    private val statut: String = "actif",
    private val utilisateur_id: Int = 2,
    private val categorie_id: Int = 1,
    private val created_at: String =
        LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        )
)
