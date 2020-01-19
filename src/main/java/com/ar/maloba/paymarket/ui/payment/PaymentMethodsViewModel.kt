package com.ar.maloba.paymarket.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ar.maloba.paymarket.repository.PaymentMethodsRepository
import com.ar.maloba.paymarket.repository.entity.CardIssuersEntity
import com.ar.maloba.paymarket.repository.entity.PaymentMethodEntity
import com.ar.maloba.paymarket.utils.AbsentLiveData
import com.ar.maloba.paymarket.utils.Resource
import javax.inject.Inject

class PaymentMethodsViewModel
@Inject
constructor(private val paymentMethodsRepository: PaymentMethodsRepository) : ViewModel() {

    private val allPaymentMethodsMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var getAllPaymentMethods: LiveData<Resource<List<PaymentMethodEntity>>>

    private val cardIssuersMutableLiveData: MutableLiveData<String> = MutableLiveData()
    var getCardIssuers: LiveData<Resource<List<CardIssuersEntity>>>

    init {
        getAllPaymentMethods = Transformations.switchMap(allPaymentMethodsMutableLiveData) {
            if (!it) AbsentLiveData.create() else paymentMethodsRepository.getAllPaymentMethods()
        }

        getCardIssuers = Transformations.switchMap(cardIssuersMutableLiveData) {
            if (it.isEmpty()) AbsentLiveData.create() else paymentMethodsRepository.getCardIssuers(it)
        }
    }

    fun loadAllPaymentMethods(fetch: Boolean) {
        if (allPaymentMethodsMutableLiveData.value == fetch) {
            return
        }
        allPaymentMethodsMutableLiveData.value = fetch
    }

    fun getCardIssuersFor(payment: String) {
        if(cardIssuersMutableLiveData.value === payment) {
            return
        }
        cardIssuersMutableLiveData.value = payment
    }
}