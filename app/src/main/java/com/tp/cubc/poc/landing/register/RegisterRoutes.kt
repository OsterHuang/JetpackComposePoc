package com.tp.cubc.poc.landing.register

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tp.cubc.poc.landing.LandingRoutes

enum class RegisterRoutes() {
    Term,
    Ocr,
    Personal
}

fun NavGraphBuilder.registerGraph(navController: NavController) {
    val goOcr = { navController.navigate(RegisterRoutes.Ocr.name) }
    val goPersonal = { navController.navigate(RegisterRoutes.Personal.name) }
    navigation(RegisterRoutes.Term.name, LandingRoutes.Register.name) {
        composable(RegisterRoutes.Term.name) { RegisterTermScreen(goOcr) }
        composable(RegisterRoutes.Ocr.name) { RegisterOcrScreen(goPersonal) }
        composable(RegisterRoutes.Personal.name) { RegisterPersonalScreen() }
    }
}