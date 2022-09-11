package com.tp.cubc.poc.transfer.typedialog

import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import androidx.navigation.compose.navigation
import com.tp.cubc.poc.transfer.TransferMainViewModel
import com.tp.cubc.poc.transfer.TransferRoutes
import com.tp.cubc.poc.transfer.TransferTypeRouter
import com.tp.cubc.poc.transfer.model.OtherBank

enum class TransferTypeRoutes {
    TransferTypeIndex,
    TransferTypeLevel1,
    TransferTypeOtherBank,
    TransferTypeOtherBankType
}

/**
 * 決定轉帳類型的Dialog
 */
class TransferTypeDialogRouter (navController: NavController) {
    val goBack = fun() {
        navController.popBackStack()
    }
    val goOtherBank = {
        navController.navigate(TransferTypeRoutes.TransferTypeOtherBank.name)
    }
    val goOtherBankType = {
        navController.navigate(TransferTypeRoutes.TransferTypeOtherBankType.name)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
fun NavGraphBuilder.transferTypeGraph(
    routeName: String,
    navController: NavController
) {
    val transferTypeDialogRouter = TransferTypeDialogRouter(navController)
    val transferTypeRouter = TransferTypeRouter(navController)

    navigation(
        startDestination = TransferRoutes.TransferMain.name,
        route = routeName
    ) {
        dialog(TransferTypeRoutes.TransferTypeLevel1.name) { navBackStackEntry ->
            val transferMainEntry = remember(navBackStackEntry) {
                navController.getBackStackEntry(TransferRoutes.TransferMain.name)
            }
            val transferMainViewModel = hiltViewModel<TransferMainViewModel>(transferMainEntry)

            TransferTypeLevel1(
                transferMainViewModel = transferMainViewModel,
                goCubc = transferTypeRouter.goCubc,
                goOtherBank = transferTypeDialogRouter.goOtherBank,
                goBakongWallet = transferTypeRouter.goBakongWallet
            )
        }
        dialog(
            route = TransferTypeRoutes.TransferTypeOtherBank.name,
            dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
        ) { navBackStackEntry ->
            val transferMainEntry = remember(navBackStackEntry) {
                navController.getBackStackEntry(TransferRoutes.TransferMain.name)
            }
            val transferMainViewModel = hiltViewModel<TransferMainViewModel>(transferMainEntry)

            TransferTypeOtherBank(
                transferMainViewModel = transferMainViewModel ,
                goOtherBankType = transferTypeDialogRouter.goOtherBankType
            )
        }
        dialog(
            route = TransferTypeRoutes.TransferTypeOtherBankType.name,
            dialogProperties = DialogProperties(usePlatformDefaultWidth = true)
        ) { navBackStackEntry ->
            val transferMainEntry = remember(navBackStackEntry) {
                navController.getBackStackEntry(TransferRoutes.TransferMain.name)
            }
            val transferMainViewModel = hiltViewModel<TransferMainViewModel>(transferMainEntry)
            TransferTypeOtherBankType(
                transferMainViewModel = transferMainViewModel,
                goOtherLocalFast = transferTypeRouter.goOtherLocalFast,
                goOtherBakong = transferTypeRouter.goOtherBakong
            )
        }
    }
}

