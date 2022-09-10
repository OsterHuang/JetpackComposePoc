package com.tp.cubc.poc.transfer.typedialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tp.cubc.poc.R
import com.tp.cubc.poc.transfer.TransferMainViewModel
import com.tp.cubc.poc.transfer.model.TransferType
import com.tp.cubc.poc.ui.component.Divider

@Composable
fun TransferTypeLevel1(
    goCubc: () -> Unit,
    goOtherBank: () -> Unit,
    goBakongWallet: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(8.dp, 15.dp)
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(30.dp)
            ),
    ) {
        TextButton(
            onClick = {
                goCubc()
            },
            Modifier.padding(4.dp, 8.dp)
        ) {
            Text(stringResource(id = R.string.cubc))
        }
        Divider()
        TextButton(
            onClick = goOtherBank,
            Modifier.padding(4.dp, 8.dp)
        ) {
            Text(stringResource(id = R.string.other_bank_and_wallet))
        }
        Divider()
        TextButton(
            onClick = goBakongWallet,
            Modifier.padding(4.dp, 8.dp)
        ) {
            Text(stringResource(id = R.string.bakong_wallet))
        }
    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    TransferTypeLevel1 ({}, {}, {})
}