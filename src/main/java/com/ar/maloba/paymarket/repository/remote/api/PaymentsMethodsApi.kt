package com.ar.maloba.paymarket.repository.remote.api

import androidx.lifecycle.LiveData
import com.ar.maloba.paymarket.repository.remote.model.PaymentMethodsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PaymentsMethodsApi {

    @GET("v1/payment_methods")
    fun getPaymentMethods(@Path("public_key") publicKey: String): LiveData<ApiResponse<PaymentMethodsResponse>>

    @GET("v1/payment_methods/card_issuers")
    fun getCardIssuers(@Path("public_key") publicKey: String,
                       @Path("payment_method_id") paymentMethodId: String)

    @GET("v1/payment_methods/installments")
    fun getCardIssuers(@Path("public_key") publicKey: String,
                       @Path("amount") amount: Double,
                       @Path("payment_method_id") paymentMethodId: String,
                       @Path("issuer.id") issuerId: String)

}