package com.ar.maloba.paymarket.repository.entity

data class PaymentEntity(
    var amount: Double
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