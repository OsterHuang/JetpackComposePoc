package com.tp.cubc.poc.transfer.cubc

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

enum class CubcTransferRoutes() {
    Input,
    Confirm,
    Success,
    Failure
}

class TransferTypesRouter (navController: NavController) {
    val goConfirm = {
        navController.navigate(CubcTransferRoutes.Confirm.name)
    }
    val goSuccess = {
        navController.navigate(CubcTransferRoutes.Success.name)
    }
    val goFailure = {
        navController.navigate(CubcTransferRoutes.Failure.name)
    }
}

fun NavGraphBuilder.cubcTransferGraph(
    navController: NavController,
    routeName: String,
    goNewTransfer: () -> Unit,
    goAccount: () -> Unit,
    goHome: () -> Unit
) {

    val transferTypesRouter = TransferTypesRouter(navController)

    navigation(CubcTransferRoutes.Input.name, routeName) {
        composable(CubcTransferRoutes.Input.name) { CubcInputScreen(
            goConfirm = transferTypesRouter.goConfirm
        ) }
        composable(CubcTransferRoutes.Confirm.name) { CubcConfirmScreen(
            goSuccess = transferTypesRouter.goSuccess,
            goFailure = transferTypesRouter.goFailure
        ) }
        composable(CubcTransferRoutes.Success.name) { CubcSuccessScreen(
            goNewTransfer,
            goAccount
        ) }
        composable(CubcTransferRoutes.Failure.name) { CubcFailureScreen(
            goHome
        ) }
    }
}
