package com.tp.cubc.poc.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tp.cubc.poc.home.account.myAccountGraph
import com.tp.cubc.poc.landing.LandingRoutes

enum class HomeRoutes() {
    Home,
    Account,
    Location,
    Other
}

class HomeIndexRouter (navController: NavController) {
    val goHome = {
        navController.popBackStack()
        navController.navigate(HomeRoutes.Home.name)
    }
    val goAccount = {
        navController.popBackStack()
        navController.navigate(HomeRoutes.Account.name) }
    val goLocation = {
        navController.popBackStack()
        navController.navigate(HomeRoutes.Location.name)
    }
    val goOther = {
        navController.popBackStack()
        navController.navigate(HomeRoutes.Other.name)
    }
}

fun NavGraphBuilder.homeGraph(navController: NavController) {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route

    val homeRouter = HomeIndexRouter(navController)

    navigation(HomeRoutes.Home.name, LandingRoutes.HomeIndex.name) {
        composable(HomeRoutes.Home.name) { HomeScreen(homeRouter) }
        myAccountGraph(navController, homeRouter)
        composable(HomeRoutes.Location.name) { LocationScreen(homeRouter) }
        composable(HomeRoutes.Other.name) { OtherScreen(homeRouter) }
    }
}

