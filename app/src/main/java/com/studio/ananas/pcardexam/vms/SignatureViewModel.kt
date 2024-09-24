package com.studio.ananas.pcardexam.vms

import androidx.lifecycle.ViewModel
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignatureViewModel : ViewModel() {
    // Expose a state flow for the signature path
    private val _signaturePath = MutableStateFlow(Path())
    val signaturePath = _signaturePath.asStateFlow()

    // Called when the user touches down
    fun onTouchDown(offset: Offset) {
        val newPath = Path().apply {
            addPath(_signaturePath.value)
            moveTo(offset.x, offset.y)
        }
        _signaturePath.value = newPath
    }

    // Called when the user moves their finger
    fun onTouchMove(offset: Offset) {
        val newPath = Path().apply {
            addPath(_signaturePath.value)
            lineTo(offset.x, offset.y)
        }
        _signaturePath.value = newPath
    }

    // Clear the signature path
    fun clear() {
        _signaturePath.value = Path()
    }
}

