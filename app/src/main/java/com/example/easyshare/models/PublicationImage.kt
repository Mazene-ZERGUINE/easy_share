package com.example.easyshare.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PublicationImage(
    @SerializedName("image_id")
    val imageId: Int,
    val libelle: String,
    val lien: String,
    @SerializedName("publicationId")
    val publicationId: Int
) : Parcelable
