package com.ar.maloba.paymarket.repository.remote.model


import com.google.gson.annotations.SerializedName

object InstallmentsResponse: ArrayList<InstallmentsBean>()

data class InstallmentsBean(
    @SerializedName("agreements")
    val agreements: Any?,
    @SerializedName("issuer")
    val issuer: IssuerBean,
    @SerializedName("merchant_account_id")
    val merchantAccountId: Any?,
    @SerializedName("payer_costs")
    val payerCosts: List<PayerCostBean>,
    @SerializedName("payment_method_id")
    val paymentMethodId: String,
    @SerializedName("payment_type_id")
    val paymentTypeId: String,
    @SerializedName("processing_mode")
    val processingMode: String
)