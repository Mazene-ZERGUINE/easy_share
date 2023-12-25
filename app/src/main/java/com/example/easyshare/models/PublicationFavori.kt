package com.example.easyshare.models

import com.google.gson.annotations.SerializedName

data class PublicationFavori(
    @SerializedName("publication_id")
    val publicationId: Int,
    @SerializedName("utilisateur_id")
    val utilisateurId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val publication: Publication
)
