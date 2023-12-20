package com.example.easyshare.models
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Commentaire(
    @SerializedName("commentaire")
    val comment: String,
    @SerializedName("commentaire_id")
    val commentId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("publication_id")
    val publicationId: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("utilisateur")
    val user: Utilisateur,
    @SerializedName("utilisateur_id")
    val utilisateurId: Int
) : Parcelable
