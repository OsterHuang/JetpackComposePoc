package com.tp.cubc.poc.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tp.cubc.poc.account.myAccountGraph
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
        navController.navigate(HomeRoutes.Home.name) {
            launchSingleTop = true
        }
    }
    val goAccount = {
        navController.popBackStack()
        navController.navigate(HomeRoutes.Account.name) {
            launchSingleTop = true
        }
    }
    val goLocation = {
        navController.popBackStack()
        navController.navigate(HomeRoutes.Location.name) {
            launchSingleTop = true
        }
    }
    val goOther = {
        navController.popBackStack()
        navController.navigate(HomeRoutes.Other.name) {
            launchSingleTop = true
        }
    }
}

fun NavGraphBuilder.homeGraph(
    navController: NavController,
    goTransfer: () -> Unit
) {
    val homeRouter = HomeIndexRouter(navController)

    val goUiExample = {
        navController.navigate("ComponentSampleScreen")
    }

    navigation(HomeRoutes.Home.name, LandingRoutes.HomeIndex.name) {
        composable(HomeRoutes.Home.name) {
            HomeScreen(
                homeRouter,
                goTransfer,
                goUiExample
            )
        }
        myAccountGraph(navController, homeRouter)
        composable(HomeRoutes.Location.name) { LocationScreen(homeRouter) }
        composable(HomeRoutes.Other.name) { OtherScreen(homeRouter) }
    }
}

