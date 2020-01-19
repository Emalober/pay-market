package com.ar.maloba.paymarket.repository.remote.model


import com.google.gson.annotations.SerializedName

object CardIssuersResponse: ArrayList<CardIssuersBean>()

data class CardIssuersBean(
    @SerializedName("id")
    val id: String,
    @SerializedName("merchant_account_id")
    val merchantAccountId: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("processing_mode")
    val processingMode: String,
    @SerializedName("secure_thumbnail")
    val secureThumbnail: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)