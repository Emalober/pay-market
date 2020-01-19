package com.ar.maloba.paymarket.repository.remote.model


import com.google.gson.annotations.SerializedName

data class IssuerBean(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("secure_thumbnail")
    val secureThumbnail: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)