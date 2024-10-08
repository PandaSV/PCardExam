package com.studio.ananas.pcardexam.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Splash(){
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.fillMaxWidth(0.6f), text = "Disclaimer: I caught a flu on that bike ride and this task took me much longer than I planned.")
        Spacer(modifier = Modifier.size(64.dp))
        Text(text = "reticulating splines...")
        CircularProgressIndicator()
    }

}

@Preview
@Composable
fun PreviewSplash(){
    Splash()
}