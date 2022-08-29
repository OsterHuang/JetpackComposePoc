package com.tp.cubc.poc.landing.register

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
fun RegisterOcrScreen(
    nextStep: () -> Unit,
    goBackToLogin: () -> Unit
) {
    BackHandler {
        goBackToLogin()
    }

    TreeBg {
        Column(Modifier.fillMaxSize()) {
            Text(
                stringResource(id = R.string.register_ocr),
                color = colorResource(R.color.white)
            )
            Spacer(modifier = Modifier.weight(1.0f))
            Button(
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 24.dp),
                onClick = nextStep
            ) {
                Text("Next")
            }
        }
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    RegisterOcrScreen({}, {})
}