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

enum class LocalFastRoutes() {
    Input,
    Confirm,
    Success,
    Failure
}

class TransferTypesRouter (navController: NavController) {
    val goConfirm = {
        navController.navigate(LocalFastRoutes.Confirm.name)
    }
    val goSuccess = {
        navController.navigate(LocalFastRoutes.Success.name)
    }
    val goFailure = {
        navController.navigate(LocalFastRoutes.Failure.name)
    }
}

fun NavGraphBuilder.localFastTransferGraph(
    routeName: String,
    navController: NavController,
    goNewTransfer: () -> Unit,
    goAccount: () -> Unit,
    goHome: () -> Unit
) {

    val transferTypesRouter = TransferTypesRouter(navController)

    navigation(LocalFastRoutes.Input.name, routeName) {
        composable(LocalFastRoutes.Input.name) { navBackStackEntry ->
            val parentEntry = remember(navBackStackEntry) {
                navController.getBackStackEntry(TransferRoutes.TransferMain.name)
            }
            val transferMainViewModel = hiltViewModel<TransferMainViewModel>(parentEntry)
            LocalFastInputScreen(
                transferMainViewModel = transferMainViewModel,
                goConfirm = transferTypesRouter.goConfirm
            )
        }
        composable(LocalFastRoutes.Confirm.name) {

            LocalFastConfirmScreen(
                goSuccess = transferTypesRouter.goSuccess,
                goFailure = transferTypesRouter.goFailure
            )
        }
        composable(LocalFastRoutes.Success.name) {LocalFastSuccessScreen(
            goNewTransfer,
            goAccount
        ) }
        composable(LocalFastRoutes.Failure.name) { LocalFastFailureScreen(
            goHome
        ) }
    }
}

