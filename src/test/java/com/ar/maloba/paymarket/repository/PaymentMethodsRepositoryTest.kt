package com.ar.maloba.paymarket.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ar.maloba.paymarket.repository.entity.PaymentMethodEntity
import com.ar.maloba.paymarket.repository.remote.api.ApiResponse
import com.ar.maloba.paymarket.repository.remote.api.PaymentsMethodsApi
import com.ar.maloba.paymarket.repository.remote.model.PaymentMethodBean
import com.ar.maloba.paymarket.repository.remote.model.PaymentMethodsResponse
import com.ar.maloba.paymarket.util.ApiUtil
import com.ar.maloba.paymarket.util.TestUtil.initJsonArray
import com.ar.maloba.paymarket.utils.Resource
import com.google.gson.JsonArray
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.io.IOException


@RunWith(JUnit4::class)
class PaymentMethodsRepositoryTest {

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var paymentMethodsRepository: PaymentMethodsRepository

    private lateinit var jsonBody: JsonArray

    private val paymentsMethodsApiApi = Mockito.mock(PaymentsMethodsApi::class.java)

    @Before
    @Throws(IOException::class, InterruptedException::class)
    fun setUp() {
        jsonBody= initJsonArray("method_response_200.json")
        paymentMethodsRepository = PaymentMethodsRepository(paymentsMethodsApiApi)
    }


    @Test
    fun getAllPaymentMethods() {
        val list: List<PaymentMethodBean> = listOf()
        val userGetAllResponse = PaymentMethodsResponse(list)
        val userGetAllApiResponseLiveData: LiveData<ApiResponse<PaymentMethodsResponse>> =
            ApiUtil.successCall(userGetAllResponse)

        `when`(paymentsMethodsApiApi.getPaymentMethods("xxxxxxxxxxxx"))
            .thenReturn(userGetAllApiResponseLiveData)

        val observer = mock<Observer<Resource<List<PaymentMethodEntity>>>>()
        paymentMethodsRepository.getAllPaymentMethods().observeForever(observer)

    }

}

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)