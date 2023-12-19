package com.example.easyshare.models

import com.google.gson.annotations.SerializedName

data class UtilisateurX(
    val commentaires: List<CommentaireX>,
    val departement: String,
    val email: String,
    @SerializedName("mot_de_passe")
    val motDePasse: String,
    val nom: String,
    val prenom: String,
    val pseudonyme: String,
    val role: String,
    val sessions: List<Any>,
    val statut: String,
    @SerializedName("utilisateur_id")
    val utilisateurId: Int,
    @SerializedName("utilisateur_signalements")
    val utilisateurSignalements: List<Any>,
    val ville: String
)
