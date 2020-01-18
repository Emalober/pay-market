package com.ar.maloba.paymarket.repository.entity

data class PaymentMethodEntity(
    val id: String,
    val name: String,
    val type: String,
    val thumbnail: String
)