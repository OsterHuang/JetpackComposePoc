package com.tp.cubc.poc.transfer.cubc

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mbanking.cubc.myAccount.repository.dataModel.MyBankAccount
import com.mbanking.cubc.myAccount.repository.dataModel.QueryAccountInfoResponseBodyResult
import com.tp.cubc.poc.account.repository.AccountApi
import com.tp.cubc.poc.account.repository.AccountRemoteDataSource
import com.tp.cubc.poc.transfer.TransferMainViewModel
import com.tp.cubc.poc.transfer.dataModel.BankAccount
import com.tp.cubc.poc.transfer.dataModel.TransferType
import com.tp.cubc.poc.ui.bg.BasicBg
import com.tp.cubc.poc.ui.component.*
import com.tp.cubc.poc.ui.component.dropdown.DropdownField
import com.tp.cubc.poc.ui.component.dropdown.DropdownItemSelectable
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import com.tp.cubc.poc.util.constant.CubcCurrency
import com.tp.cubc.poc.util.http.HttpRequestBody
import com.tp.cubc.poc.util.http.HttpResponseBody
import retrofit2.Response
import java.math.BigDecimal
import java.text.SimpleDateFormat

@Composable
fun CubcConfirmScreen(
    transferMainViewModel: TransferMainViewModel,
    cubcTransferViewModel: CubcTransferViewModel,
    goSuccess: (CubcSuccessResultDetail) -> Unit,
    goFailure: () -> Unit,
    goBack: () -> Unit,
) {
    // -- private fun --
    val clickToSuccessPage = fun() {
        val computedAmount = "${transferMainViewModel.fromAccount.value?.getCurrency()?.symbol}${cubcTransferViewModel.transferAmount.value}"
        val computedFromAccount = transferMainViewModel.fromAccount.value?.run {
            "${getCurrency().name} $account $nickname "
        } ?: "---"
        val computedTransferDate = cubcTransferViewModel.transferDate.value.run {
            SimpleDateFormat("yyyy/MM/dd").format(this) + " (Immediate)"
        }
        goSuccess(CubcSuccessResultDetail(
            transferAmount = computedAmount,
            toAccount = cubcTransferViewModel.toAccount.value ?: "",
            fromAccount = computedFromAccount,
            transferDate = computedTransferDate
        ))
    }

    // -- Display Computed --
    val computedAmount = "${transferMainViewModel.fromAccount.value?.getCurrency()?.symbol}${cubcTransferViewModel.transferAmount.value}"
    val computedFromAccount = transferMainViewModel.fromAccount.value?.run { formattedBalance } ?: "---"
    val computedTransferDate = cubcTransferViewModel.transferDate.value.run {
        SimpleDateFormat("yyyy/MM/dd").format(this) + " (Immediate)"
    }

    val transactionDetailItems = listOf(
        TransactionDetailItem("Transfer Amount", computedAmount),
        TransactionDetailItem("Beneficiary Account Number", cubcTransferViewModel.toAccount.value ?: ""),
        TransactionDetailItem("From Account", computedFromAccount),
        TransactionDetailItem("Transfer Date", computedTransferDate),
    )

    BasicBg {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                title = { TopBarTitleText(text = "Confirmation") },
                backgroundColor = MaterialTheme.colors.background,
                elevation = 12.dp
            )

            Subtitle(
                text = "Transfer Detail",
                modifier = Modifier.padding(16.dp, 20.dp, 16.dp, 4.dp)
            )

            TransactionDetail(transactionDetailItems)

            Subtitle(
                text = "Verification Method",
                modifier = Modifier.padding(16.dp, 4.dp, 16.dp, 4.dp)
            )

            RoundedBorderColumn() {
                DropdownField<DropdownItemSelectable>(
                    value = object: DropdownItemSelectable {
                        override fun getLabel(): String {
                            return "Bio"
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {}
                )
            }

            Spacer(Modifier.weight(1f))

            BottomButtonArea {
                Button(
                    modifier = Modifier.fillMaxWidth().height(42.dp),
                    onClick = { clickToSuccessPage() }
                ) {
                    Text("Next")
                }
            }
        }
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewCubcConfirmScreen() {
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
    val cubcTransferViewModel = CubcTransferViewModel(Application()).apply {
        transferAmount.value = BigDecimal("10000")
        toAccount.value = "40007837-101"
    }
    CubcAppTheme() {
        CubcConfirmScreen(
            transferMainViewModel,
            cubcTransferViewModel = cubcTransferViewModel,
            {},
            {},
        ) {}
    }
}
