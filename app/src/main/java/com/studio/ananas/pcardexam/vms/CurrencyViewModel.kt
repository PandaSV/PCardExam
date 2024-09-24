package com.studio.ananas.pcardexam.vms
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studio.ananas.pcardexam.network.RetrofitInstance
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CurrencyViewModel : ViewModel() {

    private val _rates = MutableStateFlow<Map<String, Double>?>(null)
    val _baseCurrency = MutableStateFlow("USD")
    val _amount = MutableStateFlow(0)
    val rates: StateFlow<Map<String, Double>?> = _rates

    val baseCurrency = _baseCurrency.asStateFlow()
    val amount = _amount.asStateFlow()

    fun fetchRates() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getLatestRates(
                    apiKey = "fca_live_3gKPjX0iso2MReo6YkXIBIXJIK2ETLoesijwhWpF",
                    baseCurrency = baseCurrency.value
                )
                _rates.value = response.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateBaseCurrency(currency: String){
        _baseCurrency.value = currency
    }

    fun updateAmount(amount: Int){
        _amount.value = amount
    }
}
