package com.example.easyshare.models
import com.google.gson.annotations.SerializedName

data class Data(
    val categorie: Categorie,
    @SerializedName("publication_id")
    val publicationId: Int,
    @SerializedName("commentaires")
    val comments: List<Commentaire>,
    @SerializedName("created_at")
    val createdAt: String,
    val description: String,
    val images: List<String>,
    @SerializedName("publication_favoris")
    val favoriteProducts: List<Any>,
    val status: String,
    val titre: String,
    @SerializedName("updated_at")
    val updatedAt: Any,
    val utilisateur: Utilisateur,
    @SerializedName("utilisateur_id")
    val utilisateurId: Int
)
