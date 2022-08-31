package com.tp.cubc.poc.transfer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.R
import com.tp.cubc.poc.transfer.typedialog.TransferTypeRouter
import com.tp.cubc.poc.ui.bg.TreeBg
import com.tp.cubc.poc.ui.component.TitleText

@Composable
fun TransferMainScreen(transferTypeRouter: TransferTypeRouter) {
    TreeBg {
        Column(Modifier.fillMaxSize()) {
            Text(
                stringResource(id = R.string.transfer),
                color = colorResource(R.color.white)
            )

            TitleText("下拉選單")

            Spacer(Modifier.weight(1.0f))

            Button(onClick = transferTypeRouter.openTransferType) {
                Text("Open Dialog")
            }
        }
    }
}


//@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Preview
@Composable
private fun PreviewScreen() {
    val navController = rememberNavController()
    TransferMainScreen(TransferTypeRouter(navController))
}