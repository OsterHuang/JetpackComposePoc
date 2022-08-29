package com.tp.cubc.poc.transfer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.R
import com.tp.cubc.poc.ui.bg.TreeBg

@Composable
fun TransferMainScreen(transferTypesRouter: TransferTypesRouter) {
    TreeBg {
        Column(Modifier.fillMaxSize()) {
            Text(
                stringResource(id = R.string.transfer),
                color = colorResource(R.color.white)
            )

            Button(onClick = transferTypesRouter.goCubc) {
                Text("Cubc")
            }

            Button(onClick = transferTypesRouter.goBakongWallet) {
                Text("Bakong Wallet")
            }

            Button(onClick = transferTypesRouter.goOtherBakong) {
                Text("Other Bank Bakong")
            }

            Button(onClick = transferTypesRouter.goOtherLocalFast) {
                Text("Local Bank")
            }
        }
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    val navController = rememberNavController()
    TransferMainScreen(TransferTypesRouter(navController))
}