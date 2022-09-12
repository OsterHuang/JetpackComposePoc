package com.tp.cubc.poc.transfer.cubc

import android.os.Bundle
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.tp.cubc.poc.transfer.TransferMainViewModel
import com.tp.cubc.poc.transfer.TransferRoutes
import com.tp.cubc.poc.transfer.cubc.CubcConstant.Companion.ARG_SUCCESS_RESULT_DETAIL
import com.tp.cubc.poc.ui.component.TransactionDetailItem

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
    val goSuccess = fun(resultDetail: CubcSuccessResultDetail) {
        /**
         * 因為 popUpTo(TransferRoutes.TransferMain.name) { inclusive = true } 把viewModel都回收了，
         * 所以這邊必須要把數據用參數的方式傳入成功頁
         */
        navController.navigate(
            route = CubcTransferRoutes.CubcTransferSuccess.name
        ) {
            popUpTo(TransferRoutes.TransferMain.name) { inclusive = true }
        }
        navController.currentBackStackEntry?.arguments?.putParcelable(ARG_SUCCESS_RESULT_DETAIL, resultDetail)
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
        composable(CubcTransferRoutes.CubcTransferSuccess.name) {
            val cubcSuccessResultDetail = it//navController.previousBackStackEntry
                ?.arguments?.getParcelable<CubcSuccessResultDetail>(ARG_SUCCESS_RESULT_DETAIL)

            CubcSuccessScreen(
                resultDetail = cubcSuccessResultDetail ?: CubcSuccessResultDetail(),
                goNewTransfer = goNewTransfer,
                goAccount = goAccount)
        }
        composable(CubcTransferRoutes.CubcTransferFailure.name) { CubcFailureScreen(
            goHome
        ) }
    }
}

