package com.example.easyshare.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UtilisateurSignalement(
    @SerializedName("created_at")
    val createdAt: String,
    val description: String,
    @SerializedName("signale_id")
    val signaleId: String,
    @SerializedName("signaleur_id")
    val signaleurId: String,
    val statut: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("utilisateur_s")
    val user: Utilisateur
) : Parcelable
