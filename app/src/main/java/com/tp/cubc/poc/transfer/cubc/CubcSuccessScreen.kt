package com.tp.cubc.poc.transfer.cubc

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.R
import com.tp.cubc.poc.ui.bg.BasicBg
import com.tp.cubc.poc.ui.component.*
import com.tp.cubc.poc.ui.theme.CubcAppTheme

@Composable
fun CubcSuccessScreen(
    resultDetail: CubcSuccessResultDetail,
    goNewTransfer: () -> Unit,
    goAccount: () -> Unit,
) {

    val transactionDetailItems = listOf(
        TransactionDetailItem("Transfer Amount", resultDetail.transferAmount),
        TransactionDetailItem("Transfer To", "CUBC"),
        TransactionDetailItem("Beneficiary Account Number", resultDetail.toAccount),
        TransactionDetailItem("From Account", resultDetail.fromAccount),
        TransactionDetailItem("Transfer Date", resultDetail.transferDate),
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

            Spacer(Modifier.height(12.dp))
            Image(
                painter = painterResource(R.drawable.ic_result_success),
                contentDescription = "Success"
            )
            TitleText(text = "Succeed", Modifier)

            TransactionDetail(transactionDetailItems)

            Spacer(Modifier.weight(1f))

            BottomButtonArea {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp),
                    onClick = { goNewTransfer() }
                ) {
                    Text("New Transfer")
                }

                Spacer(Modifier.height(12.dp))

                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp),
                    onClick = { goAccount() }
                ) {
                    Text("Home")
                }
            }
        }
    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Composable
private fun PreviewScreen() {
    val resultDetail = CubcSuccessResultDetail(
         "$10,000.0",
         "12345000004079",
         "USD 1079-4423-770169 USD Acc 1",
         "01/12/2021 (Immediate)",
    )

    CubcAppTheme() {
        CubcSuccessScreen(
            resultDetail,
            {},
            {},
        )
    }
}