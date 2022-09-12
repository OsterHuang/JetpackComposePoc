package com.tp.cubc.poc.transfer.typedialog

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.R
import com.tp.cubc.poc.transfer.TransferMainViewModel
import com.tp.cubc.poc.transfer.model.OtherBank
import com.tp.cubc.poc.transfer.model.TransferType

@Composable
fun TransferTypeOtherBankType(
    transferMainViewModel: TransferMainViewModel,
    goOtherLocalFast: () -> Unit,
    goOtherBakong: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(8.dp, 15.dp)
            .width(340.dp)
//            .height(200.dp)
            .wrapContentHeight()
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(30.dp)
            ),
    ) {
        val isBakong = transferMainViewModel.transferToBank.value?.isBakong ?: false
        val isFast = transferMainViewModel.transferToBank.value?.isFast ?: false
        val isLocal = transferMainViewModel.transferToBank.value?.isLocal ?: false

        if (isBakong) {
            TextButton(
                onClick = {
                    transferMainViewModel.transferType.value = TransferType.Bakong
                    goOtherBakong()
                },
                Modifier.padding(4.dp, 8.dp).fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.bakong), textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth())
            }
        }

        if (isBakong && isFast) Divider()

        if (isFast) {
            TextButton(
                onClick = {
                    transferMainViewModel.transferType.value = TransferType.Fast
                    goOtherLocalFast()
                },
                Modifier.padding(4.dp, 8.dp).fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.fast_transfer), textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth())
            }
        }

        if (isFast && isLocal) Divider()

        if (isLocal) {
            TextButton(
                onClick = {
                    transferMainViewModel.transferType.value = TransferType.Local
                    goOtherLocalFast()
                },
                Modifier.padding(4.dp, 8.dp).fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.local_bank_transfer), textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    val transferMainViewModel = TransferMainViewModel(Application())
    transferMainViewModel.transferToBank.value = OtherBank(
        "Preivew the bank type",
        isBakong = true,
        isLocal = true,
        isFast = true
    )

    TransferTypeOtherBankType (
        transferMainViewModel = transferMainViewModel,
        {},
        {},
    )
}