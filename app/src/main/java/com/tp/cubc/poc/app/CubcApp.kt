package com.tp.cubc.poc.app

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
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

            if (appViewModel.loading.value > 0) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x22000000)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(60.dp).zIndex(5f)
                    )
                }
            }
        }
    }
}