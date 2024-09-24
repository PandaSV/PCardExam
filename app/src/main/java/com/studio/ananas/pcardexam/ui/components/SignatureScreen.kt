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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.studio.ananas.pcardexam.R
import com.studio.ananas.pcardexam.vms.SignatureViewModel

@Composable
fun SignatureScreen(
    padding: PaddingValues,
    signatureViewModel: SignatureViewModel,
    onNavigateBack: () -> Unit,
    onNavigateToReceipt: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
    ) {

        //Nested column to center between clock and buttons more lazily
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 32.dp), verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.caption_signature)
            )
            SignatureBox(viewModel = signatureViewModel)
            OutlinedButton(onClick = { signatureViewModel.clear() }) {
                Text(text = stringResource(id = R.string.caption_clear))
            }
        }

        //Bottom buttons
        Row(modifier = Modifier.padding(horizontal = 32.dp)) {
            OutlinedButton(modifier = Modifier.weight(1F), onClick = onNavigateToReceipt) {
                Text(text = stringResource(id = R.string.caption_submit))
            }
            Spacer(modifier = Modifier.size(16.dp))
            OutlinedButton(modifier = Modifier.weight(1F), onClick = onNavigateBack) {
                Text(text = stringResource(id = R.string.caption_exit))
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
    }

}
