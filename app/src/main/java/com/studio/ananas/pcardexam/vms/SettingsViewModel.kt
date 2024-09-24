package com.studio.ananas.pcardexam.vms

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel : ViewModel() {
    // Internal MutableStateFlow for settings
    private val _settingPayments = MutableStateFlow(true)
    private val _settingCurrency = MutableStateFlow(true)
    private val _settingSignature = MutableStateFlow(true)
//    private val _isClockAnalog = MutableStateFlow(true)

    // Public StateFlows to expose immutable state
    val settingPayments = _settingPayments.asStateFlow()
    val settingCurrency = _settingCurrency.asStateFlow()
    val settingSignature = _settingSignature.asStateFlow()

    // Function to update settings
    fun toggleSettingPayments() {
        _settingPayments.value = !_settingPayments.value
    }

    fun toggleSettingCurrency() {
        _settingCurrency.value = !_settingCurrency.value
    }

    fun toggleSettingSignature() {
        _settingSignature.value = !_settingSignature.value
    }
}