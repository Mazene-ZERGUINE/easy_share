package com.example.easyshare.models

import com.google.gson.annotations.SerializedName

data class CommentaireX(
    val commentaire: String,
    @SerializedName("commentaire_id")
    val commentaireId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("publication_id")
    val publicationId: Int,
    @SerializedName("updated_at")
    val updatedAt: Any,
    @SerializedName("utilisateur_id")
    val utilisateurId: Int
)
