package com.tp.cubc.poc.transfer

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tp.cubc.poc.home.HomeRoutes
import com.tp.cubc.poc.home.account.myAccountGraph
import com.tp.cubc.poc.landing.LandingRoutes
import com.tp.cubc.poc.transfer.cubc.cubcTransferGraph
import com.tp.cubc.poc.transfer.otherbakong.OtherBakongInputScreen
import com.tp.cubc.poc.transfer.otherlocalfast.LocalFastInputScreen

enum class TransferRoutes() {
    Index,
    Main,
    Cubc,
    BakongWallet,
    OtherLocalFast,
    OtherBakong
}

class TransferTypesRouter (navController: NavController) {
    val goCubc = {
        navController.navigate(TransferRoutes.Cubc.name)
    }
    val goBakongWallet = {
        navController.navigate(TransferRoutes.BakongWallet.name)
    }
    val goOtherLocalFast = {
        navController.navigate(TransferRoutes.OtherLocalFast.name)
    }
    val goOtherBakong = {
        navController.navigate(TransferRoutes.OtherBakong.name)
    }
}

fun NavGraphBuilder.transferGraph(navController: NavController) {

    val transferTypesRouter = TransferTypesRouter(navController)

    val goNewTransfer = {
        navController.popBackStack()
        navController.navigate(TransferRoutes.Index.name) {
            popUpTo(TransferRoutes.Index.name) { inclusive = true }
        }
    }
    val goAccount = {
        navController.popBackStack()
        navController.navigate(HomeRoutes.Account.name) {
            popUpTo(HomeRoutes.Account.name) { inclusive = true }
        }
    }
    val goHome = {
        navController.popBackStack()
        navController.navigate(HomeRoutes.Home.name) {
            popUpTo(HomeRoutes.Home.name) { inclusive = true }
        }
    }

    navigation(TransferRoutes.Main.name, TransferRoutes.Index.name) {
        composable(TransferRoutes.Main.name) { TransferMainScreen(transferTypesRouter) }
        cubcTransferGraph(
            routeName = TransferRoutes.Cubc.name,
            navController = navController,
            goNewTransfer = goNewTransfer,
            goAccount = goAccount,
            goHome = goHome
        )
        composable(TransferRoutes.OtherBakong.name) { OtherBakongInputScreen() }
        composable(TransferRoutes.OtherLocalFast.name) { LocalFastInputScreen() }
    }
}

