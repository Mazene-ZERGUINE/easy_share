package com.example.easyshare.models

import com.google.gson.annotations.SerializedName

data class UtilisateurSignalement(
    @SerializedName("created_at")
    val createdAt: Any,
    val description: Any,
    @SerializedName("signale_id")
    val signaleId: Any,
    @SerializedName("signaleur_id")
    val signaleurId: Any,
    val statut: Any,
    @SerializedName("updated_at")
    val updatedAt: Any,
    @SerializedName("utilisateur_s")
    val utilisateurS: Any
)
