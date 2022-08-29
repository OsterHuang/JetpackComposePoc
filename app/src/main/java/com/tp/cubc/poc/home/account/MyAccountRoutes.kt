package com.tp.cubc.poc.home.account

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

enum class MyAccountRoutes() {
    MyAccountList,
    MyAccountDetail
}

fun NavGraphBuilder.myAccountGraph(navController: NavController) {
    val goList = { navController.navigate(MyAccountRoutes.MyAccountList.name) }
    val goDetail = { navController.navigate(MyAccountRoutes.MyAccountDetail.name) }
    navigation(MyAccountRoutes.MyAccountList.name, "MyAccountRoutes") {
        composable(MyAccountRoutes.MyAccountList.name) { MyAccountListScreen(goDetail) }
        composable(MyAccountRoutes.MyAccountDetail.name) { MyAccountDetailScreen(goList) }
    }
}

