package com.tp.cubc.poc.transfer.cubc

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
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
        navController.navigate(CubcTransferRoutes.CubcTransferSuccess.name)
    }
    val goFailure = {
        navController.navigate(CubcTransferRoutes.CubcTransferFailure.name)
    }
}

fun NavGraphBuilder.cubcTransferGraph(
    routeName: String,
    navController: NavController,
    goNewTransfer: () -> Unit,
    goAccount: () -> Unit,
    goHome: () -> Unit
) {

    val cubcTransferRouter = CubcTransferRouter(navController)

    navigation(CubcTransferRoutes.CubcTransferInput.name, routeName) {
        composable(CubcTransferRoutes.CubcTransferInput.name) { navBackStackEntry ->
            val parentEntry = remember(navBackStackEntry) {
                navController.getBackStackEntry(TransferRoutes.TransferMain.name)
            }
            val transferMainViewModel = hiltViewModel<TransferMainViewModel>(parentEntry)
            CubcInputScreen(
                transferMainViewModel = transferMainViewModel,
                goConfirm = cubcTransferRouter.goConfirm
            )
        }
        composable(CubcTransferRoutes.CubcTransferConfirm.name) {

            CubcConfirmScreen(
                goSuccess = cubcTransferRouter.goSuccess,
                goFailure = cubcTransferRouter.goFailure
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

