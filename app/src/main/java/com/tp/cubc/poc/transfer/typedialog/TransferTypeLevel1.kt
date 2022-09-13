package com.tp.cubc.poc.transfer.typedialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tp.cubc.poc.R
import com.tp.cubc.poc.transfer.TransferMainViewModel
import com.tp.cubc.poc.transfer.dataModel.TransferType
import com.tp.cubc.poc.ui.component.Divider

@Composable
fun TransferTypeLevel1(
    transferMainViewModel: TransferMainViewModel,
    goCubc: () -> Unit,
    goOtherBank: () -> Unit,
    goBakongWallet: () -> Unit,
) {
    val textButtonModifier = Modifier.padding(4.dp, 8.dp).fillMaxWidth()

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
                transferMainViewModel.transferType.value = TransferType.Cubc
                goCubc()
            },
            textButtonModifier,
        ) {
            Text(
                stringResource(id = R.string.cubc),
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Divider()
        TextButton(
            onClick = goOtherBank,
            textButtonModifier
        ) {
            Text(
                stringResource(id = R.string.other_bank_and_wallet),
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Divider()
        TextButton(
            onClick = {
                transferMainViewModel.transferType.value = TransferType.Bakong
                goBakongWallet()
            },
            textButtonModifier
        ) {
            Text(
                stringResource(id = R.string.bakong_wallet),
                textAlign = TextAlign.Left,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    TransferTypeLevel1 (TransferMainViewModel(hiltViewModel()), {}, {}, {})
}