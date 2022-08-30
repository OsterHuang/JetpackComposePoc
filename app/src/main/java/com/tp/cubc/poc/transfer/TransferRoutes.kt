package com.tp.cubc.poc.transfer

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tp.cubc.poc.home.HomeRoutes
import com.tp.cubc.poc.transfer.bakongwallet.BakongWalletInputScreen
import com.tp.cubc.poc.transfer.cubc.cubcTransferGraph
import com.tp.cubc.poc.transfer.otherbakong.OtherBakongInputScreen
import com.tp.cubc.poc.transfer.otherlocalfast.LocalFastInputScreen
import com.tp.cubc.poc.transfer.typedialog.TransferTypeRouter
import com.tp.cubc.poc.transfer.typedialog.TransferTypeRoutes
import com.tp.cubc.poc.transfer.typedialog.transferTypeGraph

enum class TransferRoutes() {
    TransferIndex,
    TransferMain,
    Cubc,
    BakongWallet,
    OtherLocalFast,
    OtherBakong
}

fun NavGraphBuilder.transferGraph(navController: NavController) {

    val transferTypesRouter = TransferTypeRouter(navController)

    val goNewTransfer = {
        navController.popBackStack()
        navController.navigate(TransferRoutes.TransferIndex.name) {
            popUpTo(TransferRoutes.TransferIndex.name) { inclusive = true }
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

    navigation(TransferRoutes.TransferMain.name, TransferRoutes.TransferIndex.name) {
        composable(TransferRoutes.TransferMain.name) { TransferMainScreen(transferTypesRouter) }
        transferTypeGraph(
            routeName = TransferTypeRoutes.TransferTypeIndex.name,
            navController = navController
        )
        cubcTransferGraph(
            routeName = TransferRoutes.Cubc.name,
            navController = navController,
            goNewTransfer = goNewTransfer,
            goAccount = goAccount,
            goHome = goHome
        )
        composable(TransferRoutes.BakongWallet.name) { BakongWalletInputScreen() }
        composable(TransferRoutes.OtherBakong.name) { OtherBakongInputScreen() }
        composable(TransferRoutes.OtherLocalFast.name) { LocalFastInputScreen() }
    }
}

