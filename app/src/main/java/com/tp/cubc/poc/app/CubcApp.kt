package com.tp.cubc.poc.app

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.ui.theme.CubcAppTheme

@Composable
fun CubcApp() {
    val navHostController = rememberNavController()
    val appViewModel: CubcAppViewModel = viewModel(LocalContext.current as ComponentActivity)

    CubcAppTheme(appViewModel.isDarkModeState.value) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            CubcAppNavHost(navHostController = navHostController)
        }
    }
}