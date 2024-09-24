package com.studio.ananas.pcardexam.managers

import com.studio.ananas.pcardexam.entities.Payment

class PaymentManager {
    fun instantiatePayment(amount: Int, payments: Int, currency: String): Payment{
        return Payment(amount = amount, currencyCode = currency, payments = payments)
    }
}