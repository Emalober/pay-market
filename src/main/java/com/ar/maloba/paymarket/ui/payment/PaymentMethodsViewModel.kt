package com.ar.maloba.paymarket.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ar.maloba.paymarket.repository.PaymentMethodsRepository
import com.ar.maloba.paymarket.repository.entity.CardIssuersEntity
import com.ar.maloba.paymarket.repository.entity.InstallmentsEntity
import com.ar.maloba.paymarket.repository.entity.PaymentEntity
import com.ar.maloba.paymarket.repository.entity.PaymentMethodEntity
import com.ar.maloba.paymarket.utils.AbsentLiveData
import com.ar.maloba.paymarket.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentMethodsViewModel
@Inject
constructor(private val paymentMethodsRepository: PaymentMethodsRepository) : ViewModel() {

    private val allPaymentMethodsMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    var getAllPaymentMethods: LiveData<Resource<List<PaymentMethodEntity>>>

    private val paymentMethodIdMutableLiveData: MutableLiveData<String> = MutableLiveData()
    var getCardIssuers: LiveData<Resource<List<CardIssuersEntity>>>

    private val issuerMutableLiveData: MutableLiveData<CardIssuersEntity> = MutableLiveData()
    private var amount: Float = 0F

    private val installmentMutableLiveData: MutableLiveData<InstallmentsEntity> = MutableLiveData()
    var getInstallments: LiveData<Resource<List<InstallmentsEntity>>>

    private var paymentResult: PaymentEntity? = null
    fun getPaymentResult() = paymentResult

    init {
        getAllPaymentMethods = Transformations.switchMap(allPaymentMethodsMutableLiveData) {
            if (!it) AbsentLiveData.create() else paymentMethodsRepository.getAllPaymentMethods()
        }

        getCardIssuers = Transformations.switchMap(paymentMethodIdMutableLiveData) {
            if (it == null) AbsentLiveData.create() else paymentMethodsRepository.getCardIssuers(it)
        }

        getInstallments = Transformations.switchMap(issuerMutableLiveData) {
            if (it == null) AbsentLiveData.create() else paymentMethodsRepository.getInstallments(amount, paymentMethodIdMutableLiveData.value!!, it.id)
        }
    }

    fun setAmount(amount: Float) {
        this.paymentResult = PaymentEntity(amount)
        this.amount = amount
    }

    fun loadAllPaymentMethods(fetch: Boolean) {
        if (allPaymentMethodsMutableLiveData.value == fetch) {
            return
        }
        allPaymentMethodsMutableLiveData.value = fetch
    }

    fun selectedPaymentMethod(paymentMethod: PaymentMethodEntity) {
        paymentResult?.paymentMethod = paymentMethod
    }

    fun getCardIssuersFor(payment: String) {
        if(paymentMethodIdMutableLiveData.value === payment) {
            return
        }
        paymentMethodIdMutableLiveData.value = payment
    }

    fun selectedCardIssuer(isuuar: CardIssuersEntity) {
        if(issuerMutableLiveData.value === isuuar) {
            return
        }
        issuerMutableLiveData.value = isuuar
        paymentResult?.cardIssuers = isuuar

    }

    fun selectedInstallment(installment: InstallmentsEntity) {
        if(installmentMutableLiveData.value === installment) {
            return
        }
        installmentMutableLiveData.value = installment
        paymentResult?.installments = installment
    }

    fun finishPayment() {
        amount = 0F
        paymentMethodIdMutableLiveData.value = null
        issuerMutableLiveData.value = null
        installmentMutableLiveData.value = null
    }
}