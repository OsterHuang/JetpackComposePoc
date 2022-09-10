package com.tp.cubc.poc.app

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CubcAppViewModel: ViewModel() {
    var isDarkModeState = mutableStateOf(false)
}