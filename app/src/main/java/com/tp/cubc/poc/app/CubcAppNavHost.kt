package com.tp.cubc.poc.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tp.cubc.poc.home.homeGraph
import com.tp.cubc.poc.landing.LandingRoutes
import com.tp.cubc.poc.landing.LoginScreen
import com.tp.cubc.poc.landing.SplashScreen
import com.tp.cubc.poc.landing.applyMobileBank.ApplyMobileBankEntryScreen
import com.tp.cubc.poc.landing.register.registerGraph

@Composable
fun CubcAppNavHost(navHostController: NavHostController) {
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
    fun goHome() {
        navHostController.navigate(LandingRoutes.HomeIndex.name)
    }

    fun goLogin() {
        navHostController.navigate(LandingRoutes.Login.name) {
            popUpTo(LandingRoutes.Login.name) { inclusive = false }
        }
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
            LoginScreen(
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
        registerGraph(navHostController)
        composable(LandingRoutes.ApplyMobileBank.name) {
            ApplyMobileBankEntryScreen()
        }
        homeGraph(navHostController)
    }
}