package com.tp.cubc.poc.transfer.typedialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.R
import com.tp.cubc.poc.transfer.model.OtherBank
import com.tp.cubc.poc.ui.component.Divider

@Composable
fun TransferTypeOtherBankType(
    otherBank: OtherBank?,
    goOtherLocalFast: () -> Unit,
    goOtherBakong: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp, 15.dp)
            .width(340.dp).height(200.dp)
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(30.dp)
            ),
    ) {
        if (otherBank?.isBakong == true) {
            TextButton(
                onClick = goOtherBakong,
                Modifier.padding(4.dp, 8.dp)
            ) {
                Text(stringResource(id = R.string.bakong))
            }
            Divider()
        }

        if (otherBank?.isFast == true) {
            TextButton(
                onClick = goOtherLocalFast,
                Modifier.padding(4.dp, 8.dp)
            ) {
                Text(stringResource(id = R.string.fast_transfer))
            }
            Divider()
        }

        if (otherBank?.isLocal == true) {
            TextButton(
                onClick = goOtherLocalFast,
                Modifier.padding(4.dp, 8.dp)
            ) {
                Text(stringResource(id = R.string.local_bank_transfer))
            }
        }
    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    TransferTypeOtherBankType (
        OtherBank(
            name ="Oster Test",
            isBakong = true,
            isFast = true,
            isLocal = true,
        ),
        {},
        {}
    )
}