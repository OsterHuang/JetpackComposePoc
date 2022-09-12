package com.tp.cubc.poc.app

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tp.cubc.poc.example.ComponentSampleScreen
import com.tp.cubc.poc.home.homeGraph
import com.tp.cubc.poc.landing.LandingRoutes
import com.tp.cubc.poc.landing.LoginScreen
import com.tp.cubc.poc.landing.LoginViewModel
import com.tp.cubc.poc.landing.SplashScreen
import com.tp.cubc.poc.landing.applyMobileBank.ApplyMobileBankEntryScreen
import com.tp.cubc.poc.landing.register.registerGraph
import com.tp.cubc.poc.transfer.TransferRoutes
import com.tp.cubc.poc.transfer.transferGraph

@Composable
fun CubcAppNavHost(navHostController: NavHostController) {
    // -- Landing --
    fun nextToLogin() {
        navHostController.popBackStack()
        navHostController.navigate(LandingRoutes.Login.name) {
            popUpTo(LandingRoutes.Login.name) { inclusive = false }
        }
    }
    fun goRegister() {
        navHostController.navigate(LandingRoutes.Register.name)
    }
    fun goApplyMobileBank() {
        navHostController.navigate(LandingRoutes.ApplyMobileBank.name)
    }
    val goBackLogin = {
        navHostController.navigate(LandingRoutes.Login.name) {
            popUpTo(LandingRoutes.Login.name) { inclusive = true }
        }
    }

    // -- In Login Status--
    fun goHome() {
        navHostController.navigate(LandingRoutes.HomeIndex.name)
    }

    val goTransfer = {
        navHostController.navigate(TransferRoutes.TransferIndex.name)
    }

    NavHost(
        navController = navHostController,
        startDestination = LandingRoutes.Splash.name,
        route = "CubcApp"
    ) {
        composable(LandingRoutes.Splash.name) {
            SplashScreen() { nextToLogin() }
        }
        composable(LandingRoutes.Login.name) {
            val loginViewModel: LoginViewModel = hiltViewModel() //　只有此畫面使用viewModel沒有流程
            LoginScreen(
                loginViewModel,
                goRegister = {
                    goRegister()
                },
                goApplyMobileBank = {
                    goApplyMobileBank()
                },
                goHome = {
                    goHome()
                }
            )
        }
        registerGraph(navHostController, goBackLogin)
        composable(LandingRoutes.ApplyMobileBank.name) {
            ApplyMobileBankEntryScreen()
        }
        homeGraph(navHostController, goTransfer)
        transferGraph(navHostController)
        // -- UI Examples never mind the hard code --
        composable("ComponentSampleScreen") {
            ComponentSampleScreen()
        }
    }
}