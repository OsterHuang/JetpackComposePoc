package com.tp.cubc.poc.transfer.cubc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tp.cubc.poc.R
import com.tp.cubc.poc.transfer.TransferMainViewModel
import com.tp.cubc.poc.ui.bg.TreeBg
import com.tp.cubc.poc.ui.component.TitleText
import com.tp.cubc.poc.ui.theme.CubcAppTheme

@Composable
fun CubcInputScreen(transferMainViewModel: TransferMainViewModel = TransferMainViewModel(), goConfirm: () -> Unit) {

    TreeBg {
        Column(Modifier.fillMaxSize()) {
            TitleText("Cubc Input")

            Text(
                text = transferMainViewModel.transferType.value?.name ?: "No type here??? The state is error",
                color = colorResource(R.color.white)
            )

            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Enter Email") },
                placeholder = { Text("Enter Email") },
            )

            Spacer(Modifier.weight(1.0f))

            Button(onClick = goConfirm) {
                Text("Next")
            }
        }
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    CubcAppTheme {
        CubcInputScreen(TransferMainViewModel()) {}
    }
}