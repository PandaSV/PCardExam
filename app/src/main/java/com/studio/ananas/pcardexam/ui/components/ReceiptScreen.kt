package com.studio.ananas.pcardexam.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studio.ananas.pcardexam.R
import com.studio.ananas.pcardexam.vms.CurrencyViewModel
import com.studio.ananas.pcardexam.vms.MainViewModel
import com.studio.ananas.pcardexam.vms.SettingsViewModel
import com.studio.ananas.pcardexam.vms.SignatureViewModel

@Composable
fun ReceiptScreen(
    padding: PaddingValues,
    mainViewModel: MainViewModel,
    signatureViewModel: SignatureViewModel,
    settingsViewModel: SettingsViewModel,
    currencyViewModel: CurrencyViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToConvert: () -> Unit
) {
    val settingPayments = settingsViewModel.settingPayments.collectAsState()
    val settingCurrency = settingsViewModel.settingCurrency.collectAsState()
    val settingSignature = settingsViewModel.settingSignature.collectAsState()

    val paymentsEnabled = mainViewModel.enablePayments.collectAsState()
    var isIls = mainViewModel.chosenCurrencyIsIls.collectAsState()
    var signatureEnabled = mainViewModel.requireSignature.collectAsState()
    var selectedPaymentsOption = mainViewModel.numberPayments.collectAsState()
    var amount = mainViewModel.amount.collectAsState()

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = stringResource(id = R.string.title_receipt), fontSize = 36.sp)

        //Nested column to center between clock and buttons more lazily
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 32.dp), verticalArrangement = Arrangement.Center
        ) {
            Row {
                Text(text = stringResource(id = R.string.caption_amount))
                Text(text = amount.value.toString())
            }

            if (settingPayments.value) {
                Row {
                    Text(text = stringResource(id = R.string.caption_payments))
                    Text(text = selectedPaymentsOption.value.toString())
                }
            }

            if (settingCurrency.value) {
                Row {
                    Text(text = stringResource(id = R.string.caption_currency))
                    Text(
                        text = if (isIls.value) {
                            "ILS"
                        } else {
                            "USD"
                        }
                    )
                }
            }


            if (settingSignature.value) {
                Text(text = stringResource(id = R.string.caption_signature))
                SignatureBox(viewModel = signatureViewModel)
            }

            OutlinedButton(onClick = {
                currencyViewModel.updateBaseCurrency(
                    if (isIls.value) {
                        "ILS"
                    } else {
                        "USD"
                    }
                )
                currencyViewModel.updateAmount(amount.value)
                onNavigateToConvert.invoke()
            }) {
                Text(text = stringResource(id = R.string.caption_convert))
            }


        }

        //Bottom button
        Row(modifier = Modifier.padding(horizontal = 32.dp)) {
            OutlinedButton(modifier = Modifier.weight(1F), onClick = {
                //TODO pass values to manager to wrap in data structure
                onNavigateBack.invoke()
            }) {
                Text(text = stringResource(id = R.string.caption_finish))
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}
