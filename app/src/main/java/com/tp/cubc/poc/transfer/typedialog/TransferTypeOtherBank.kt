package com.tp.cubc.poc.transfer.typedialog

import android.app.Application
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
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
import com.tp.cubc.poc.ui.component.Divider
import com.tp.cubc.poc.util.constant.CubcCurrency
import com.tp.cubc.poc.util.http.HttpRequestBody
import com.tp.cubc.poc.util.http.HttpResponseBody
import retrofit2.Response
import java.math.BigDecimal


@Composable
fun TransferTypeOtherBank(
    transferMainViewModel: TransferMainViewModel,
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
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(30.dp)
            ),
    ) {
        LazyColumn(
            Modifier.padding(8.dp, 16.dp)
        ) {
                mutableState.value.forEach { otherBank ->
                    item {
                        TextButton(
                            onClick = {
                                transferMainViewModel.transferToBank.value = otherBank
                                goOtherBankType()
                            },
                            Modifier.padding(4.dp, 8.dp).fillMaxWidth()
                        ) {
                            Text(otherBank.name, textAlign = TextAlign.Left, modifier = Modifier.fillMaxWidth())
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
            branchCode = "????????????",
            account = "01110110300273",
            curr = CubcCurrency.USD.name,
            balance = BigDecimal("2174.63"),
            nickname = "???????????? Digit USD"
        )
        transferType.value = TransferType.Cubc
    }
    TransferTypeOtherBank (transferMainViewModel) {}
}