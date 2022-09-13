package com.tp.cubc.poc.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tp.cubc.poc.R
import com.tp.cubc.poc.ui.bg.TreeBg

@Composable
fun MyAccountDetailScreen(
    goBackList: () -> Unit
) {
    TreeBg {
        Column(Modifier.fillMaxSize()) {
            Text(
                stringResource(id = R.string.my_account_Detail),
                color = colorResource(R.color.white)
            )
            Button(onClick = goBackList) {
                Text("Back to Account List")
            }
        }
    }

}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=680")
@Composable
private fun PreviewScreen() {
    MyAccountDetailScreen () {}
}