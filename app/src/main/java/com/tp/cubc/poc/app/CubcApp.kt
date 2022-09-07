package com.tp.cubc.poc.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.ui.theme.CubcAppTheme

var isDarkModeState = mutableStateOf(true)


@Composable
fun CubcApp() {
    val navHostController = rememberNavController()

    CubcAppTheme(isDarkModeState.value) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            CubcAppNavHost(navHostController = navHostController)
        }
    }
}