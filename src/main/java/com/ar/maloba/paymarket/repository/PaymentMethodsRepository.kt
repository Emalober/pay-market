package com.ar.maloba.paymarket.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.ar.maloba.paymarket.BuildConfig.PUBLIC_KEY
import com.ar.maloba.paymarket.repository.entity.CardIssuersEntity
import com.ar.maloba.paymarket.repository.entity.InstallmentsEntity
import com.ar.maloba.paymarket.repository.entity.PaymentEntity
import com.ar.maloba.paymarket.repository.entity.PaymentMethodEntity
import com.ar.maloba.paymarket.repository.remote.api.ApiResponse
import com.ar.maloba.paymarket.repository.remote.api.PaymentsMethodsApi
import com.ar.maloba.paymarket.repository.remote.model.CardIssuersResponse
import com.ar.maloba.paymarket.repository.remote.model.InstallmentsResponse
import com.ar.maloba.paymarket.repository.remote.model.PaymentMethodsResponse
import com.ar.maloba.paymarket.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentMethodsRepository
@Inject
constructor(private val api: PaymentsMethodsApi) {

    fun getAllPaymentMethods(): LiveData<Resource<List<PaymentMethodEntity>>> {
        return object : ProcessedNetworkResource<PaymentMethodsResponse, List<PaymentMethodEntity>>() {
            override fun createCall(): LiveData<ApiResponse<PaymentMethodsResponse>> =
                api.getPaymentMethods(PUBLIC_KEY)

            override fun processResponse(response: PaymentMethodsResponse): List<PaymentMethodEntity>? =
                response.map {
                    PaymentMethodEntity(it.id, it.name, it.paymentTypeId, it.secureThumbnail)
                }
        }.asLiveData()
    }

    fun getCardIssuers(paymentMethodId: String): LiveData<Resource<List<CardIssuersEntity>>> {
        return object : ProcessedNetworkResource<CardIssuersResponse, List<CardIssuersEntity>>() {
            override fun createCall(): LiveData<ApiResponse<CardIssuersResponse>> =
                api.getCardIssuers(PUBLIC_KEY, paymentMethodId)

            override fun processResponse(response: CardIssuersResponse): List<CardIssuersEntity>? =
                response.map {
                    CardIssuersEntity(it.id, it.name, it.secureThumbnail)
                }
        }.asLiveData()
    }

    fun getInstallments(amount: Float, paymentMethodId: String, issuerId: String): LiveData<Resource<List<InstallmentsEntity>>> {
        return object : ProcessedNetworkResource<InstallmentsResponse, List<InstallmentsEntity>>() {
            override fun createCall(): LiveData<ApiResponse<InstallmentsResponse>> =
                api.getInstallments(PUBLIC_KEY, amount, paymentMethodId, issuerId)

            override fun processResponse(response: InstallmentsResponse): List<InstallmentsEntity>? {
                val result = response.map {
                    it.payerCosts.map { payer ->
                        InstallmentsEntity(payer.installments, payer.recommendedMessage, payer.totalAmount)
                    }
                }
                return if (result.isNotEmpty())
                    result[0]
                else
                    listOf()
            }

        }.asLiveData()
    }

}