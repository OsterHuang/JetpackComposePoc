package com.tp.cubc.poc.transfer.bakongwallet

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tp.cubc.poc.transfer.TransferMainViewModel
import com.tp.cubc.poc.transfer.TransferRoutes

enum class BakongWalletTransferRoutes() {
    BakongWalletTransferInput,
    BakongWalletTransferConfirm,
    BakongWalletTransferSuccess,
    BakongWalletTransferFailure
}

class BakongWalletTransferRouter (navController: NavController) {
    val goConfirm = {
        navController.navigate(BakongWalletTransferRoutes.BakongWalletTransferConfirm.name)
    }
    val goSuccess = {
        navController.navigate(BakongWalletTransferRoutes.BakongWalletTransferSuccess.name)
    }
    val goFailure = {
        navController.navigate(BakongWalletTransferRoutes.BakongWalletTransferFailure.name)
    }
}

fun NavGraphBuilder.bakongWalletTransferGraph(
    routeName: String,
    navController: NavController,
    goNewTransfer: () -> Unit,
    goAccount: () -> Unit,
    goHome: () -> Unit
) {
    val bakongWalletTransferRouter = BakongWalletTransferRouter(navController)

    navigation(BakongWalletTransferRoutes.BakongWalletTransferInput.name, routeName) {
        composable(BakongWalletTransferRoutes.BakongWalletTransferInput.name) { navBackStackEntry ->
            val parentEntry = remember(navBackStackEntry) {
                navController.getBackStackEntry(TransferRoutes.TransferMain.name)
            }
            val transferMainViewModel = hiltViewModel<TransferMainViewModel>(parentEntry)
            BakongWalletInputScreen(
                transferMainViewModel = transferMainViewModel,
                goConfirm = bakongWalletTransferRouter.goConfirm
            )
        }
        composable(BakongWalletTransferRoutes.BakongWalletTransferConfirm.name) {
            BakongWalletConfirmScreen(
                goSuccess = bakongWalletTransferRouter.goSuccess,
                goFailure = bakongWalletTransferRouter.goFailure
            )
        }
        composable(BakongWalletTransferRoutes.BakongWalletTransferSuccess.name) { BakongWalletSuccessScreen(
            goNewTransfer,
            goAccount
        ) }
        composable(BakongWalletTransferRoutes.BakongWalletTransferFailure.name) { BakongWalletFailureScreen(
            goHome
        ) }
    }
}

