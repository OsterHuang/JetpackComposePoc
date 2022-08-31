package com.tp.cubc.poc.transfer.typedialog

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import androidx.navigation.compose.navigation
import com.tp.cubc.poc.transfer.TransferRoutes
import com.tp.cubc.poc.transfer.model.OtherBank

enum class TransferTypeRoutes {
    TransferTypeIndex,
    TransferTypeLevel1,
    TransferTypeOtherBank,
    TransferTypeOtherBankType
}

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

class TransferTypeRouter (navController: NavController) {
    val openTransferType = {
        navController.navigate(TransferTypeRoutes.TransferTypeLevel1.name)
    }
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

@OptIn(ExperimentalComposeUiApi::class)
fun NavGraphBuilder.transferTypeGraph(routeName: String, navController: NavController) {
    val transferTypeDialogRouter = TransferTypeDialogRouter(navController)
    val transferTypeRouter = TransferTypeRouter(navController)

    var otherBank: OtherBank? = null

    navigation(
        startDestination = TransferRoutes.TransferMain.name,
        route = routeName
    ) {
        dialog(TransferTypeRoutes.TransferTypeLevel1.name) {
            TransferTypeLevel1(
                goCubc = transferTypeRouter.goCubc,
                goOtherBank = transferTypeDialogRouter.goOtherBank,
                goBakongWallet = transferTypeRouter.goBakongWallet
            )
        }
        dialog(
            route = TransferTypeRoutes.TransferTypeOtherBank.name,
            dialogProperties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            TransferTypeOtherBank(
                chooseBank = { it -> otherBank = it } ,
                goOtherBankType = transferTypeDialogRouter.goOtherBankType
            )
        }
        dialog(
            route = TransferTypeRoutes.TransferTypeOtherBankType.name,
            dialogProperties = DialogProperties(usePlatformDefaultWidth = true)
        ) {
            TransferTypeOtherBankType(
                otherBank = otherBank,
                goOtherLocalFast = transferTypeRouter.goOtherLocalFast,
                goOtherBakong = transferTypeRouter.goOtherBakong
            )
        }
    }
}

