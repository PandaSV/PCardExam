package com.studio.ananas.pcardexam.ui.components

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.util.Calendar

@Composable
fun Clock(isAnalog: Boolean){
    var time: Pair<Int, Int> by remember {
        mutableStateOf(Pair(0,0))
    }
    LaunchedEffect(Unit) {
        while (true) {

            time = getCurrentHoursAndMinutes()
            delay(10000)

        }
    }


    if(isAnalog){
        AnalogClock(hours = time.first, minutes = time.second)
    }else{
        DigitalClock(hours = time.first, minutes = time.second)
    }
}

fun getCurrentHoursAndMinutes(): Pair<Int, Int> {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = System.currentTimeMillis()

    val hours = calendar.get(Calendar.HOUR_OF_DAY) // Use HOUR_OF_DAY for 24-hour format
    val minutes = calendar.get(Calendar.MINUTE)

    return Pair(hours, minutes)
}

@Composable
fun AnalogClock(hours: Int, minutes: Int){
//    TODO later — had no time
}

@SuppressLint("DefaultLocale")
@Composable
fun DigitalClock(hours: Int, minutes: Int){
    Text(text = String.format("%02d:%02d", hours, minutes), fontSize = 96.sp)
}

@Preview
@Composable
fun PreviewClock(){
    Clock(isAnalog = false)
}