package com.studio.ananas.pcardexam.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.studio.ananas.pcardexam.entities.currencyNames
import com.studio.ananas.pcardexam.vms.CurrencyViewModel

@Composable
fun ConvertScreen(
    padding: PaddingValues,
    currencyViewModel: CurrencyViewModel,
    onNavigateBack: () -> Boolean
) {
    val rates by currencyViewModel.rates.collectAsState()
    val amount by currencyViewModel.amount.collectAsState()
    val baseCurrency by currencyViewModel.baseCurrency.collectAsState()

    LaunchedEffect(Unit) {
        currencyViewModel.fetchRates()
    }


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                rates?.let {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        it.forEach { (currency, rate) ->
                            CurrencyRow(currency = currency, baseCurrency= baseCurrency, rate = rate, amount = amount)
                            HorizontalDivider()
                        }
                    }
                } ?: CircularProgressIndicator()
            }


}

@SuppressLint("DefaultLocale")
@Composable
fun CurrencyRow(currency: String, baseCurrency: String, rate: Double, amount: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = currency.plus(" ( ").plus(currencyNames[currency]).plus(" ):"))
//        Text(text = "Exchange Rate: 1 $baseCurrency = $rate $currency")
        Text(text = "Exchange Rate: 1 $baseCurrency = ${String.format("%.2f", rate)} $currency")
//        Text(text = "Converted Amount:  $amount $baseCurrency = ${amount*rate} $currency")
        Text(text = "Converted Amount:  $amount $baseCurrency = ${String.format("%.2f", amount*rate)} $currency")
    }
}