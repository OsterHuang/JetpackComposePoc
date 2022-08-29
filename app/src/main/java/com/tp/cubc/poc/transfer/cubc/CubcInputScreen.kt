package com.tp.cubc.poc.transfer.cubc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tp.cubc.poc.R
import com.tp.cubc.poc.ui.bg.TreeBg
import com.tp.cubc.poc.ui.component.TitleText

@Composable
fun CubcInputScreen(goConfirm: () -> Unit) {
    TreeBg {
        Column(Modifier.fillMaxSize()) {
            Text(
                stringResource(id = R.string.transfer),
                color = colorResource(R.color.white)
            )

            TitleText("Cubc Input")
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
    CubcInputScreen() {}
}