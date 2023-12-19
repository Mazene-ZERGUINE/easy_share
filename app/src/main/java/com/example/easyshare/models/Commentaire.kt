package com.example.easyshare.models

import com.google.gson.annotations.SerializedName

data class Commentaire(
    @SerializedName("commentaire")
    val comments: String,
    @SerializedName("commentaire_id")
    val commentId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("publication_id")
    val publicationId: Int,
    @SerializedName("updated_at")
    val updatedAt: Any,
    @SerializedName("utilisateur")
    val user: Utilisateur,
    @SerializedName("utilisateur_id")
    val utilisateurId: Int
)
