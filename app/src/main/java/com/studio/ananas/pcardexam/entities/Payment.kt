package com.studio.ananas.pcardexam.entities

data class Payment(
    val amount: Int,
    val currencyCode: String,
    val payments: Int
)