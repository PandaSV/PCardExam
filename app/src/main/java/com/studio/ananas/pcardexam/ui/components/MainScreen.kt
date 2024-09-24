package com.studio.ananas.pcardexam.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.studio.ananas.pcardexam.R
import com.studio.ananas.pcardexam.vms.MainViewModel
import com.studio.ananas.pcardexam.vms.SettingsViewModel

@Composable
fun MainScreen(
    padding: PaddingValues,
    onNavigateToSignature: () -> Unit,
    onNavigateToReceipt: () -> Unit,
    onNavigateToSettings: () -> Unit,
    settingsViewModel: SettingsViewModel,
    mainViewModel: MainViewModel
) {
    val settingPayments = settingsViewModel.settingPayments.collectAsState()
    val settingCurrency = settingsViewModel.settingCurrency.collectAsState()
    val settingSignature = settingsViewModel.settingSignature.collectAsState()

    val paymentsEnabled = mainViewModel.enablePayments.collectAsState()
    var paymentsExpanded by remember { mutableStateOf(false) }
    var isIls = mainViewModel.chosenCurrencyIsIls.collectAsState()
    var signatureEnabled = mainViewModel.requireSignature.collectAsState()
    var selectedPaymentsOption = mainViewModel.numberPayments.collectAsState()
    var amount = mainViewModel.amount.collectAsState()

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            modifier = Modifier.align(alignment = Alignment.End),
            onClick = onNavigateToSettings
        ) {
            Icon(imageVector = Icons.Outlined.Settings, contentDescription = "settings icon")
        }
        Clock(isAnalog = false)

        //Nested column to center between clock and buttons more lazily
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 32.dp), verticalArrangement = Arrangement.Center
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = amount.value.toString(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { newValue -> mainViewModel.setAmount(newValue.toIntOrNull()) },
                label = { Text(text = stringResource(id = R.string.caption_amount)) })

            Spacer(modifier = Modifier.size(16.dp))

            //Payments Row
            if (settingPayments.value) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = stringResource(id = R.string.caption_payments)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = paymentsEnabled.value,
                        onCheckedChange = { mainViewModel.toggleEnablePayments() })
                    Spacer(modifier = Modifier.weight(1f))
                    if (paymentsEnabled.value) {
                        Button(onClick = { paymentsExpanded = true }) {
                            Text(selectedPaymentsOption.value.toString())
                        }
                        DropdownMenu(
                            expanded = paymentsExpanded,
                            onDismissRequest = { paymentsExpanded = false }
                        ) {
                            val range = 1..12
                            val options = range.toList()
                            options.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(text = option.toString()) },
                                    onClick = {
                                        mainViewModel.setPayments(option)
                                        paymentsExpanded = false
                                    })
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.size(16.dp))
            }

            //Currency Row
            if (settingCurrency.value) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = stringResource(id = R.string.caption_currency)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    FilterChip(
                        onClick = { mainViewModel.toggleIsIlsCurrency() },
                        label = {
                            Text("USD")
                        },
                        selected = !isIls.value,
                        leadingIcon =
                        null
                    )
                    FilterChip(
                        onClick = { mainViewModel.toggleIsIlsCurrency() },
                        label = {
                            Text("ILS")
                        },
                        selected = isIls.value,
                        leadingIcon =
                        null
                    )
                }

                Spacer(modifier = Modifier.size(16.dp))
            }

            //Signature Row
            if (settingSignature.value) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = stringResource(id = R.string.caption_signature)
                    )
                    Switch(
                        modifier = Modifier.padding(start = 16.dp),
                        checked = signatureEnabled.value,
                        onCheckedChange = { mainViewModel.toggleRequireSignature() })
                }
            }
        }

        //Bottom buttons
        Row(modifier = Modifier.padding(horizontal = 32.dp)) {
            OutlinedButton(
                modifier = Modifier.weight(1F),
                onClick =
                if (signatureEnabled.value && settingSignature.value) {
                    onNavigateToSignature
                } else {
                    onNavigateToReceipt
                }
            ) {
                Text(text = stringResource(id = R.string.caption_submit))
            }
            Spacer(modifier = Modifier.size(16.dp))
            OutlinedButton(modifier = Modifier.weight(1F), onClick = { /*TODO*/ }) {
                Text(text = stringResource(id = R.string.caption_exit))
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}

//@Preview
//@Composable
//fun MainScreenPreview() {
//    MainScreen(
//        PaddingValues(0.dp),
//        onNavigateToSettings = { },
//        onNavigateToSignature = { },
//        onNavigateToReceipt = {})
//}