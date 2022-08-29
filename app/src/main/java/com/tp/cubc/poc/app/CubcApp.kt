package com.tp.cubc.poc.app

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.landing.LandingRoutes
import com.tp.cubc.poc.landing.LoginScreen
import com.tp.cubc.poc.landing.SplashScreen
import com.tp.cubc.poc.landing.applyMobileBank.ApplyMobileBankEntryScreen
import com.tp.cubc.poc.landing.register.registerGraph
import com.tp.cubc.poc.ui.theme.PocTheme

@Composable
fun CubcApp() {
    val navHostController = rememberNavController()

    PocTheme ( darkTheme = false ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CubcAppNavHost(navHostController = navHostController)
        }
    }
}