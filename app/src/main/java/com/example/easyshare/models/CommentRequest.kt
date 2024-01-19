package com.example.easyshare.models

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class CommentRequest(
    private val commentaire: String,
    private val publication_id: Int,
    private val utilisateur_id: Int,
    private val created_at: String =
        LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        ),
    private val update_at: Nothing? = null
)
