package com.tp.cubc.poc.transfer.cubc

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.R
import com.tp.cubc.poc.transfer.TransferMainTopRegion
import com.tp.cubc.poc.transfer.TransferMainViewModel
import com.tp.cubc.poc.transfer.model.BankAccount
import com.tp.cubc.poc.transfer.model.TransferPurpose
import com.tp.cubc.poc.transfer.model.TransferType
import com.tp.cubc.poc.ui.bg.BasicBg
import com.tp.cubc.poc.ui.bg.TreeBg
import com.tp.cubc.poc.ui.component.*
import com.tp.cubc.poc.ui.component.dropdown.DropdownField
import com.tp.cubc.poc.ui.component.dropdown.DropdownItemSelectable
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import com.tp.cubc.poc.ui.theme.Green500
import com.tp.cubc.poc.util.constant.CubcCurrency
import java.math.BigDecimal
import java.text.SimpleDateFormat

@Composable
fun CubcConfirmScreen(
    transferMainViewModel: TransferMainViewModel,
    cubcTransferViewModel: CubcTransferViewModel,
    goSuccess: () -> Unit,
    goFailure: () -> Unit,
    goBack: () -> Unit,
) {
    val valueTextModifier = Modifier.padding(0.dp, 10.dp)

    val clickNext = fun() {
        goSuccess()
    }

    val computedAmount = "${transferMainViewModel.fromAccount.value?.currency?.symbol}${cubcTransferViewModel.transferAmount.value}"
    val computedFromAccount = transferMainViewModel.fromAccount.value?.run {
        "${currency.name} $accountNo $nickname "
    } ?: "---"
    val computedTransferDate = cubcTransferViewModel.transferDate.value.run {
        SimpleDateFormat("yyyy/MM/dd").format(this) + " (Immediate)"
    }

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

            RoundedBorderColumn(horizontalAlignment = Alignment.Start) {
                Subtitle2(
                    text = "Transfer Amount"
                )
                ValueText(
                    text = computedAmount,
                    modifier = valueTextModifier
                )
                Divider()
                Spacer(Modifier.height(12.dp))

                Subtitle2(
                    text = "Beneficiary Account",
                )
                ValueText(
                    text = cubcTransferViewModel.toAccount.value ?: "",
                    modifier = valueTextModifier
                )
                Divider()
                Spacer(Modifier.height(12.dp))

                Subtitle2(
                    text = "From Account",
                )
                ValueText(
                    text = computedFromAccount,
                    modifier = valueTextModifier
                )
                Divider()
                Spacer(Modifier.height(12.dp))

                Subtitle2(
                    text = "Transfer Date",
                )
                ValueText(
                    text = computedTransferDate,
                    modifier = valueTextModifier
                )
            }

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
                    onClick = { clickNext() }
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
    val transferMainViewModel = TransferMainViewModel(Application()).apply {
        fromAccount.value = BankAccount("1072-6644017", "Acc Nickname", BigDecimal("2174.63"), CubcCurrency.USD)
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
