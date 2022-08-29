package com.tp.cubc.poc.landing.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.R
import com.tp.cubc.poc.ui.bg.TreeBg

@Composable
fun RegisterTermScreen(nextStep: () -> Unit) {
    TreeBg {
        Column(Modifier.fillMaxSize()) {
            Text(
                stringResource(id = R.string.register_terms),
                color = colorResource(R.color.white)
            )
            Text("1")
            Spacer(modifier = Modifier.weight(1.0f))
            Row(Modifier.fillMaxWidth()) {
                Button(
                    modifier = Modifier.weight(1.0f).padding(0.dp, 0.dp, 0.dp, 24.dp),
                    onClick = nextStep
                ) {
                    Text("Login")
                }
                Button(
                    modifier = Modifier.weight(1.0f).padding(0.dp, 0.dp, 0.dp, 24.dp),
                    onClick = nextStep
                ) {
                    Text("Next")
                }
            }
        }
    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    RegisterTermScreen({})
}