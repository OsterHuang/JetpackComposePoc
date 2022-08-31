package com.tp.cubc.poc.landing

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.R
import com.tp.cubc.poc.ui.bg.TreeBg

@Composable
fun LoginScreen(
    goRegister: () -> Unit,
    goApplyMobileBank: () -> Unit,
    goHome: () -> Unit
) {
    val onLogin = {
        goHome()
    }

    TreeBg {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                stringResource(id = R.string.login),
                color = colorResource(R.color.white)
            )
            Spacer(modifier = Modifier.weight(1.0f))
            Column(
                Modifier.fillMaxWidth().weight(0.2f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextButton(onClick = goRegister) {
                    Text(
                        stringResource(id = R.string.go_register),
                        color = colorResource(R.color.golden_500)
                    )
                }
                TextButton(onClick = goApplyMobileBank) {
                    Text(
                        stringResource(id = R.string.apply_mobile_bank),
                        color = colorResource(R.color.golden_500)
                    )
                }
            }
            Button(
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 24.dp),
                onClick = onLogin
            ) {
                Text("Login")
            }
        }
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
fun PreviewScreen(
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val localContext = LocalContext.current

    LoginScreen({
        Toast.makeText(localContext, "Oster Test go register", Toast.LENGTH_LONG).show()
    }, {
        Toast.makeText(localContext, "Oster Test go Apply Mobile Bank", Toast.LENGTH_LONG).show()
    }, {
    })
}