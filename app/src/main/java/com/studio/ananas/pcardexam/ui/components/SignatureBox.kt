package com.studio.ananas.pcardexam.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.studio.ananas.pcardexam.vms.SignatureViewModel
import kotlinx.coroutines.coroutineScope

@Composable
fun SignatureBox(viewModel: SignatureViewModel) {
    val signaturePath by viewModel.signaturePath.collectAsState()

    LaunchedEffect(signaturePath) {

    }

    Canvas(
        modifier = Modifier
            .size(300.dp)
            .background(Color.LightGray)
            .pointerInput(Unit) {
                coroutineScope {
                    while (true) {
                        awaitPointerEventScope {
                            val event = awaitPointerEvent()
                            val offset = event.changes.first().position

                            when (event.changes.first().pressed) {
                                true -> {
                                    if (event.changes.first().previousPressed) {
                                        viewModel.onTouchMove(offset)
                                    } else {
                                        viewModel.onTouchDown(offset)
                                    }
                                    event.changes
                                        .first()
                                        .consumePositionChange()
                                }

                                false -> {
                                }
                            }
                        }
                    }
                }
            }
    ) {
        // Draw the existing path (the signature)
        drawPath(
            path = viewModel.signaturePath.value,
            color = Color.Black,
            style = Stroke(width = 4f)
        )
    }
}