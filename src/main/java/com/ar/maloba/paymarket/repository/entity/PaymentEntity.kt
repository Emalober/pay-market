package com.ar.maloba.paymarket.repository.entity

data class PaymentEntity(
    var amount: Float = 0F,
    var paymentMethod: PaymentMethodEntity? = null,
    var cardIssuers: CardIssuersEntity? = null,
    var installments: InstallmentsEntity? = null
)

data class PaymentMethodEntity(
    val id: String,
    val name: String,
    val type: String,
    val thumbnail: String
)

data class CardIssuersEntity(
    val id: String,
    val name: String,
    val thumbnail: String
)

data class InstallmentsEntity(
    val installments: Int,
    val message: String,
    val totalAmount: Float
)