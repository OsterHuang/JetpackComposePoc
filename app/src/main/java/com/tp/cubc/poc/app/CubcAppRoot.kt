package com.tp.cubc.poc.app

import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.tp.cubc.poc.ui.component.Loading
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import kotlinx.coroutines.launch

@Composable
fun CubcApp() {
    val navHostController = rememberNavController()
    val appViewModel: CubcAppViewModel = viewModel(LocalContext.current as ComponentActivity)

    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(true) { // Set true to execute on first recompositio
        coroutineScope.launch {
            appViewModel.requireAccessToken()
        }
    }

    CubcAppTheme(appViewModel.isDarkModeState.value) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            CubcAppNavHost(navHostController = navHostController)

            Loading(visible = appViewModel.loading.value > 0)
        }
    }
}