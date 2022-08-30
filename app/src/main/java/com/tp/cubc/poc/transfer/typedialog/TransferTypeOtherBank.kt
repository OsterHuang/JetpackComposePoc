package com.tp.cubc.poc.transfer.typedialog

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.R
import com.tp.cubc.poc.transfer.model.OtherBank
import com.tp.cubc.poc.ui.component.Divider


@Composable
fun TransferTypeOtherBank(
    chooseBank: (OtherBank) -> Unit,
    goOtherBankType: () -> Unit
) {
    val mutableState = remember { mutableStateOf(arrayOf(
        OtherBank("May Bank", isBakong = true, isLocal = false, isFast = true),
        OtherBank("Yoda Spirit", isBakong = true, isLocal = true, isFast = true),
        OtherBank("Mojito", isBakong = true, isLocal = true, isFast = false),
        OtherBank("Uniform Fed", isBakong = true, isLocal = false, isFast = false),
    )) }

    Log.d("Oster", "the list is: ${mutableState.value}")

    Column(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.9f)
            .padding(8.dp, 15.dp)
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(30.dp)
            ),
    ) {
        LazyColumn {
            items(mutableState.value.size) {
                mutableState.value.forEach { otherBank ->
                    TextButton(
                        onClick = {
                            chooseBank(otherBank)
                            goOtherBankType()
                        },
                        Modifier.padding(8.dp, 15.dp)
                    ) {
                        Text(otherBank.name)
                    }
                    Divider()
                }
            }
        }

    }
}

@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    TransferTypeOtherBank ({}) {}
}