package com.tp.cubc.poc.home.account

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tp.cubc.poc.home.HomeIndexRouter
import com.tp.cubc.poc.home.HomeRoutes

enum class MyAccountRoutes() {
    MyAccountList,
    MyAccountDetail
}

fun NavGraphBuilder.myAccountGraph(navController: NavController, homeIndexRouter: HomeIndexRouter) {
    val goBackList = fun() { navController.popBackStack() }
    val goDetail = { navController.navigate(MyAccountRoutes.MyAccountDetail.name) }
    navigation(MyAccountRoutes.MyAccountList.name, HomeRoutes.Account.name) {
        composable(MyAccountRoutes.MyAccountList.name) { MyAccountListScreen(homeIndexRouter, goDetail) }
        composable(MyAccountRoutes.MyAccountDetail.name) { MyAccountDetailScreen(goBackList) }
    }
}

