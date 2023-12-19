package com.example.easyshare.models

import com.google.gson.annotations.SerializedName

data class Commentaire(
    val commentaire: String,
    @SerializedName("commentaire_id")
    val commentaireId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("publication_id")
    val publicationId: Int,
    @SerializedName("updated_at")
    val updatedAt: Any,
    val utilisateur: Utilisateur,
    @SerializedName("utilisateur_id")
    val utilisateurId: Int
)
