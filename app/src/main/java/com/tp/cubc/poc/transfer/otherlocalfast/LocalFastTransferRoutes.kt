package com.tp.cubc.poc.transfer.otherlocalfast

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

enum class LocalFastTransferRoutes() {
    LocalFastTransferInput,
    LocalFastTransferConfirm,
    LocalFastTransferSuccess,
    LocalFastTransferFailure
}

class LocalFastTransferRouter (navController: NavController) {
    val goConfirm = {
        navController.navigate(LocalFastTransferRoutes.LocalFastTransferConfirm.name)
    }
    val goSuccess = {
        navController.navigate(LocalFastTransferRoutes.LocalFastTransferSuccess.name)
    }
    val goFailure = {
        navController.navigate(LocalFastTransferRoutes.LocalFastTransferFailure.name)
    }
}

fun NavGraphBuilder.localFastTransferGraph(
    routeName: String,
    navController: NavController,
    goNewTransfer: () -> Unit,
    goAccount: () -> Unit,
    goHome: () -> Unit
) {

    val localFastTransferRouter = LocalFastTransferRouter(navController)

    navigation(LocalFastTransferRoutes.LocalFastTransferInput.name, routeName) {
        composable(LocalFastTransferRoutes.LocalFastTransferInput.name) { navBackStackEntry ->
            val parentEntry = remember(navBackStackEntry) {
                navController.getBackStackEntry(TransferRoutes.TransferMain.name)
            }
            val transferMainViewModel = hiltViewModel<TransferMainViewModel>(parentEntry)
            LocalFastInputScreen(
                transferMainViewModel = transferMainViewModel,
                goConfirm = localFastTransferRouter.goConfirm
            )
        }
        composable(LocalFastTransferRoutes.LocalFastTransferConfirm.name) {

            LocalFastConfirmScreen(
                goSuccess = localFastTransferRouter.goSuccess,
                goFailure = localFastTransferRouter.goFailure
            )
        }
        composable(LocalFastTransferRoutes.LocalFastTransferSuccess.name) {LocalFastSuccessScreen(
            goNewTransfer,
            goAccount
        ) }
        composable(LocalFastTransferRoutes.LocalFastTransferFailure.name) { LocalFastFailureScreen(
            goHome
        ) }
    }
}

