package com.ar.maloba.paymarket.repository

import androidx.lifecycle.LiveData
import com.ar.maloba.paymarket.BuildConfig.PUBLIC_KEY
import com.ar.maloba.paymarket.repository.entity.PaymentMethodEntity
import com.ar.maloba.paymarket.repository.remote.api.ApiResponse
import com.ar.maloba.paymarket.repository.remote.api.PaymentsMethodsApi
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
                    PaymentMethodEntity(it.id, it.name)
                }
        }.asLiveData()
    }
}