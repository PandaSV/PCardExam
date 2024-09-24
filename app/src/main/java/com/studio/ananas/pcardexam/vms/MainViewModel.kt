package com.studio.ananas.pcardexam.vms

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    // Internal MutableStateFlow for settings
    private val _amount = MutableStateFlow(0)
    private val _numberPayments = MutableStateFlow(1)
    private val _enablePayments = MutableStateFlow(false)
    private val _chosenCurrencyIsIls = MutableStateFlow(false)
    private val _requireSignature = MutableStateFlow(false)

    // Public StateFlows to expose immutable state
    val enablePayments = _enablePayments.asStateFlow()
    val amount = _amount.asStateFlow()
    val numberPayments = _numberPayments.asStateFlow()
    val chosenCurrencyIsIls = _chosenCurrencyIsIls.asStateFlow()
    val requireSignature = _requireSignature.asStateFlow()

    fun setPayments(payments: Int) {
        _numberPayments.value = payments
    }

    fun setAmount(amount: Int?) {
        _amount.value = amount?:0
    }

    fun toggleEnablePayments() {
        _enablePayments.value = !_enablePayments.value
    }

    fun toggleIsIlsCurrency() {
        _chosenCurrencyIsIls.value = !_chosenCurrencyIsIls.value
    }

    fun toggleRequireSignature() {
        _requireSignature.value = !_requireSignature.value
    }

    fun reset() {
        _amount.value = 0
        _numberPayments.value = 1
        _enablePayments.value = false
        _chosenCurrencyIsIls.value = false
        _requireSignature.value = false
    }
}