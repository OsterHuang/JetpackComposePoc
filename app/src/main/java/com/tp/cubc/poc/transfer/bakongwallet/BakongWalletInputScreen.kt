package com.tp.cubc.poc.transfer.bakongwallet

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.tp.cubc.poc.ui.component.dropdown.DropdownField
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import com.tp.cubc.poc.util.constant.CubcCurrency
import com.tp.cubc.poc.util.http.HttpRequestBody
import com.tp.cubc.poc.util.http.HttpResponseBody
import retrofit2.Response
import java.math.BigDecimal

@Composable
fun BakongWalletInputScreen(
    transferMainViewModel: TransferMainViewModel,
    goConfirm: () -> Unit,
) {
    var chosenCurrency by remember { mutableStateOf(CubcCurrency.USD) }
    val currencyItems = listOf(
        CubcCurrency.USD,
        CubcCurrency.KHR
    )

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
                    accountList = transferMainViewModel.accountList.value,
                    fromAccount = transferMainViewModel.fromAccount.value,
                    transferToBank = transferMainViewModel.transferToBank.value,
                    transferType = transferMainViewModel.transferType.value
                )
                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = "",
                    label = { Text("Bakong Account") },
                    leadingIcon = { Text("+855", Modifier.padding(12.dp, 0.dp, 2.dp, 0.dp)) },
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {}
                )
                Spacer(Modifier.height(12.dp))

                Row(
                    Modifier.fillMaxWidth().wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    OutlinedTextField(
                        value = "",
                        label = { Text("Transfer Amount") },
                        modifier = Modifier.weight(0.7f).padding(0.dp, 0.dp, 4.dp, 0.dp),
                        onValueChange = {}
                    )

                    DropdownField(
                        value = chosenCurrency,
                        items = currencyItems,
                        modifier = Modifier.width(120.dp),
                        onValueChange = { chosenCurrency = it }
                    )
                }
                Spacer(Modifier.height(12.dp))
            }

            Spacer(Modifier.weight(1f))

            BottomButtonArea {
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
private fun PreviewBakongWalletInputScreen() {
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
        )
        .apply {
            fromAccount.value = MyBankAccount(
                branchCode = "????????????",
                account = "01110110300273",
                curr = CubcCurrency.USD.name,
                balance = BigDecimal("2174.63"),
                nickname = "???????????? Digit USD"
            )
            transferType.value = TransferType.Bakong
    }

    CubcAppTheme() {
        BakongWalletInputScreen(
            transferMainViewModel,
        ) {}
    }
}