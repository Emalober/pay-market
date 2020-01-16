package com.ar.maloba.paymarket.repository.remote.model


import com.google.gson.annotations.SerializedName

data class PaymentMethodsResponse(val data: List<PaymentMethodBean>)

data class PaymentMethodBean(
    @SerializedName("accreditation_time")
    val accreditationTime: Int?,
    @SerializedName("additional_info_needed")
    val additionalInfoNeeded: List<Any>,
    @SerializedName("deferred_capture")
    val deferredCapture: String,
    @SerializedName("financial_institutions")
    val financialInstitutions: List<Any>,
    @SerializedName("id")
    val id: String,
    @SerializedName("max_allowed_amount")
    val maxAllowedAmount: Int,
    @SerializedName("min_allowed_amount")
    val minAllowedAmount: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("payment_type_id")
    val paymentTypeId: String,
    @SerializedName("processing_modes")
    val processingModes: List<String>,
    @SerializedName("secure_thumbnail")
    val secureThumbnail: String,
    @SerializedName("settings")
    val settings: List<Any>,
    @SerializedName("status")
    val status: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)