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
import com.mbanking.cubc.myAccount.repository.dataModel.MyBankAccount
import com.mbanking.cubc.myAccount.repository.dataModel.QueryAccountInfoResponseBodyResult
import com.tp.cubc.poc.R
import com.tp.cubc.poc.account.repository.AccountApi
import com.tp.cubc.poc.account.repository.AccountRemoteDataSource
import com.tp.cubc.poc.transfer.TransferMainViewModel
import com.tp.cubc.poc.transfer.dataModel.OtherBank
import com.tp.cubc.poc.transfer.dataModel.TransferType
import com.tp.cubc.poc.util.constant.CubcCurrency
import com.tp.cubc.poc.util.http.HttpRequestBody
import com.tp.cubc.poc.util.http.HttpResponseBody
import retrofit2.Response
import java.math.BigDecimal

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
    val transferMainViewModel = TransferMainViewModel(
        Application(),
        AccountRemoteDataSource(
            object: AccountApi {
                override suspend fun queryAccountInfo(requestBody: HttpRequestBody): Response<HttpResponseBody<QueryAccountInfoResponseBodyResult>> {
                    return Response.success(HttpResponseBody(
                        "0000",
                        "Success",
                        QueryAccountInfoResponseBodyResult(listOf(), listOf(), listOf(), listOf())
                    ))
                }
            })
    ).apply {
        fromAccount.value = MyBankAccount(
            branchCode = "南京復興",
            account = "01110110300273",
            curr = CubcCurrency.USD.name,
            balance = BigDecimal("2174.63"),
            nickname = "南京復興 Digit USD"
        )
        transferType.value = TransferType.Cubc
    }
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