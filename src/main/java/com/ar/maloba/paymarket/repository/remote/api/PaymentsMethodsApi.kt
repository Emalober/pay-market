package com.ar.maloba.paymarket.repository.remote.api

import androidx.lifecycle.LiveData
import com.ar.maloba.paymarket.repository.remote.model.CardIssuersResponse
import com.ar.maloba.paymarket.repository.remote.model.PaymentMethodsResponse
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PaymentsMethodsApi {

    @GET("v1/payment_methods")
    fun getPaymentMethods(@Query("public_key") publicKey: String): LiveData<ApiResponse<PaymentMethodsResponse>>

    @GET("v1/payment_methods/card_issuers")
    fun getCardIssuers(@Query("public_key") publicKey: String,
                       @Query("payment_method_id") paymentMethodId: String): LiveData<ApiResponse<CardIssuersResponse>>

    @GET("v1/payment_methods/installments")
    fun getCardIssuers(@Query("public_key") publicKey: String,
                       @Query("amount") amount: Double,
                       @Query("payment_method_id") paymentMethodId: String,
                       @Query("issuer.id") issuerId: String)

}