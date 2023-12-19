package com.example.easyshare.models

import com.google.gson.annotations.SerializedName

data class Categorie(
    @SerializedName("categorie_id")
    val categorieId: Int,
    val libelle: String
)
