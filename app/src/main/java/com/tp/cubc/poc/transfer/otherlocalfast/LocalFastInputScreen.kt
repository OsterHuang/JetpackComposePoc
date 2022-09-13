package com.tp.cubc.poc.transfer.otherlocalfast

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbanking.cubc.myAccount.repository.dataModel.MyBankAccount
import com.mbanking.cubc.myAccount.repository.dataModel.QueryAccountInfoResponseBodyResult
import com.tp.cubc.poc.account.repository.AccountApi
import com.tp.cubc.poc.account.repository.AccountRemoteDataSource
import com.tp.cubc.poc.transfer.TransferMainTopRegion
import com.tp.cubc.poc.transfer.TransferMainViewModel
import com.tp.cubc.poc.transfer.dataModel.BankAccount
import com.tp.cubc.poc.transfer.dataModel.TransferType
import com.tp.cubc.poc.ui.bg.BasicBg
import com.tp.cubc.poc.ui.component.BottomButtonArea
import com.tp.cubc.poc.ui.component.RoundedBorderColumn
import com.tp.cubc.poc.ui.component.TopBarTitleText
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import com.tp.cubc.poc.util.constant.CubcCurrency
import com.tp.cubc.poc.util.http.HttpRequestBody
import com.tp.cubc.poc.util.http.HttpResponseBody
import retrofit2.Response
import java.math.BigDecimal

@Composable
fun LocalFastInputScreen(
    transferMainViewModel: TransferMainViewModel,
    goConfirm: () -> Unit,
) {
    BasicBg {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                title = { TopBarTitleText(text = "Transfer") },
                backgroundColor = MaterialTheme.colors.background,
                elevation = 12.dp
            )

            RoundedBorderColumn {
                TransferMainTopRegion(
                    transferMainViewModel = transferMainViewModel
                )
                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = "",
                    label = { Text("Beneficiary Account Number") },
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {}
                )
                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = "",
                    label = { Text("Beneficiary Name") },
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {}
                )
                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = "",
                    label = { Text("Transfer Amount") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = { Text("KHR") },
                    onValueChange = {}
                )
                Spacer(Modifier.height(12.dp))
            }

            Spacer(Modifier.weight(1f))

            BottomButtonArea {
                Row(
                    Modifier.fillMaxWidth().background(Color.Transparent).padding(0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        modifier = Modifier.background(Color.Transparent).padding(0.dp),
                        checked = true,
                        onCheckedChange = {},
                    )
                    Text("I agree with terms and conditions", color = Color.White)
                }

                Button(
                    modifier = Modifier.fillMaxWidth().height(42.dp),
                    onClick = { goConfirm() }
                ) {
                    Text("Next")
                }
            }
        }
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewLocalFastInputScreen() {
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

    CubcAppTheme() {
        LocalFastInputScreen(
            transferMainViewModel,
        ) {}
    }
}