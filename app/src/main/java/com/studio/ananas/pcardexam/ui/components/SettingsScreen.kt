package com.studio.ananas.pcardexam.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.studio.ananas.pcardexam.R
import com.studio.ananas.pcardexam.vms.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    padding: PaddingValues,
    onNavigateBack: () -> Unit,
    settingsViewModel: SettingsViewModel
) {
    val settingPayments = settingsViewModel.settingPayments.collectAsState()
    val settingCurrency = settingsViewModel.settingCurrency.collectAsState()
    val settingSignature = settingsViewModel.settingSignature.collectAsState()

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(title = { Text(text = stringResource(id = R.string.title_settings)) },
            actions = {
                IconButton(
                    onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "settings icon"
                    )
                }
            })


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.setting_payments)
            )
            Switch(
                modifier = Modifier.padding(start = 16.dp),
                checked = settingPayments.value,
                onCheckedChange = { settingsViewModel.toggleSettingPayments() })
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.setting_currency)
            )
            Switch(
                modifier = Modifier.padding(start = 16.dp),
                checked = settingCurrency.value,
                onCheckedChange = { settingsViewModel.toggleSettingCurrency() })
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.setting_signature)
            )
            Switch(
                modifier = Modifier.padding(start = 16.dp),
                checked = settingSignature.value,
                onCheckedChange = { settingsViewModel.toggleSettingSignature() })
        }
    }
}

