package com.example.easyshare.models

import com.google.gson.annotations.SerializedName

data class Publication(
    @SerializedName("categorie_id")
    val categorieId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    val description: String,
    @SerializedName("publication_id")
    val publicationId: Int,
    val statut: String,
    val titre: String,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("utilisateur_id")
    val utilisateurId: Int
)
