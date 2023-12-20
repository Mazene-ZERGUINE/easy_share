package com.example.easyshare.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Utilisateur(
    val departement: String,
    val email: String,
    @SerializedName("mot_de_passe")
    val motDePasse: String,
    val nom: String,
    val prenom: String,
    val pseudonyme: String,
    val role: String,
    val statut: String,
    @SerializedName("utilisateur_id")
    val utilisateurId: Int,
    @SerializedName("utilisateur_signalements")
    val utilisateurSignalements: List<UtilisateurSignalement>,
    val ville: String
) : Parcelable
