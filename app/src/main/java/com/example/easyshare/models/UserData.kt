package com.example.easyshare.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val email: String,
    val nom: String,
    val prenom: String,
    val pseudonyme: String,
    @SerializedName("utilisateur_id")
    val utilisateurId: Int,
    @SerializedName("mot_de_passe")
    val motDePass: String
) : Parcelable
