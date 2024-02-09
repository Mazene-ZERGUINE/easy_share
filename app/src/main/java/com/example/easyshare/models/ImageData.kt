package com.example.easyshare.models

import com.google.gson.annotations.SerializedName

data class ImageData(
    val image_id: Int,
    val libelle: String?,
    val lien: String?,
    val publication_id: Int
) {
}