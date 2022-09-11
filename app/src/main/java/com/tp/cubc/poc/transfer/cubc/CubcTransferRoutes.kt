package com.tp.cubc.poc.transfer.cubc

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tp.cubc.poc.transfer.TransferMainViewModel
import com.tp.cubc.poc.transfer.TransferRoutes

enum class CubcTransferRoutes() {
    CubcTransferInput,
    CubcTransferConfirm,
    CubcTransferSuccess,
    CubcTransferFailure
}

class CubcTransferRouter (navController: NavController) {
    val goConfirm = {
        navController.navigate(CubcTransferRoutes.CubcTransferConfirm.name)
    }
    val goSuccess = {
        navController.navigate(CubcTransferRoutes.CubcTransferSuccess.name) {
            popUpTo(TransferRoutes.TransferMain.name) {
                inclusive = true
            }
        }
    }
    val goFailure = {
        navController.navigate(CubcTransferRoutes.CubcTransferFailure.name) {
            popUpTo(TransferRoutes.TransferMain.name) {
                inclusive = true
            }
        }
    }
}

fun NavGraphBuilder.cubcTransferGraph(
    routeName: String,
    navController: NavController,
    goNewTransfer: () -> Unit,
    goAccount: () -> Unit,
    goHome: () -> Unit
) {
    val goBack = fun() {
        navController.popBackStack()
    }

    val cubcTransferRouter = CubcTransferRouter(navController)

    navigation(CubcTransferRoutes.CubcTransferInput.name, routeName) {
        composable(CubcTransferRoutes.CubcTransferInput.name) { navBackStackEntry ->
            val transferMainEntry = remember(navBackStackEntry) {
                navController.getBackStackEntry(TransferRoutes.TransferMain.name)
            }
            val transferMainViewModel = hiltViewModel<TransferMainViewModel>(transferMainEntry)

            val cubcTransferViewModel = hiltViewModel<CubcTransferViewModel>()
            CubcInputScreen(
                transferMainViewModel = transferMainViewModel,
                cubcTransferViewModel = cubcTransferViewModel,
                goConfirm = cubcTransferRouter.goConfirm
            )
        }
        composable(CubcTransferRoutes.CubcTransferConfirm.name) { navBackStackEntry ->
            val transferMainEntry = remember(navBackStackEntry) {
                navController.getBackStackEntry(TransferRoutes.TransferMain.name)
            }
            val transferMainViewModel = hiltViewModel<TransferMainViewModel>(transferMainEntry)

            val cubcTransferInputEntry = remember(navBackStackEntry) {
                navController.getBackStackEntry(CubcTransferRoutes.CubcTransferInput.name)
            }
            val cubcTransferViewModel = hiltViewModel<CubcTransferViewModel>(cubcTransferInputEntry)

            CubcConfirmScreen(
                transferMainViewModel = transferMainViewModel,
                cubcTransferViewModel = cubcTransferViewModel,
                goSuccess = cubcTransferRouter.goSuccess,
                goFailure = cubcTransferRouter.goFailure,
                goBack = goBack,
            )
        }
        composable(CubcTransferRoutes.CubcTransferSuccess.name) { CubcSuccessScreen(
            goNewTransfer,
            goAccount
        ) }
        composable(CubcTransferRoutes.CubcTransferFailure.name) { CubcFailureScreen(
            goHome
        ) }
    }
}

