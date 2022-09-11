package com.tp.cubc.poc.transfer.cubc

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
import com.tp.cubc.poc.ui.component.BottomButtonArea
import com.tp.cubc.poc.ui.component.ErrorMessage
import com.tp.cubc.poc.ui.component.RoundedBorderColumn
import com.tp.cubc.poc.ui.component.TopBarTitleText
import com.tp.cubc.poc.ui.component.dropdown.DropdownField
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import com.tp.cubc.poc.ui.theme.Green500
import com.tp.cubc.poc.util.constant.CubcCurrency
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.text.SimpleDateFormat

@Composable
fun CubcInputScreen(
    transferMainViewModel: TransferMainViewModel,
    cubcTransferViewModel: CubcTransferViewModel,
    goConfirm: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(true) { // Set true to execute on first recompositio
        coroutineScope.launch {
            transferMainViewModel.queryAccountList()
        }
    }

    // Computed
    val textTransferDate = cubcTransferViewModel.transferDate.value.run {
        SimpleDateFormat("yyyy/MM/dd").format(this) + " (Immediate)"
    }

    // Function
    val clickNext = fun () {
        val isValid = cubcTransferViewModel.validateFields()
        if (!isValid) return

        goConfirm()
    }

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
                    value = cubcTransferViewModel.toAccount.value ?: "",
                    label = { Text("Beneficiary Account Number") },
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { cubcTransferViewModel.toAccount.value = it }
                )
                ErrorMessage(cubcTransferViewModel.toAccountError.value)
                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = cubcTransferViewModel.transferAmount.value?.toString() ?: "",
                    label = { Text("Transfer Amount") },
                    trailingIcon = { Text("USD") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        cubcTransferViewModel.transferAmount.value = if (it.isNullOrBlank()) null else BigDecimal(it)
                    }
                )
                ErrorMessage(cubcTransferViewModel.transferAmountError.value)
                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = textTransferDate,
                    label = { Text("Transfer Date") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_calendar_default),
                            contentDescription = "Calendar Icon",
                            tint = Green500
                        )
                    },
                    onValueChange = {}
                )
                Spacer(Modifier.height(12.dp))

                DropdownField(
                    value = cubcTransferViewModel.purpose.value,
                    label = { Text("Purpose") },
                    items = TransferPurpose.values().asList(),
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {}
                )
                Spacer(Modifier.height(12.dp))
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
private fun PreviewScreen() {
    val transferMainViewModel = TransferMainViewModel(Application()).apply {
        fromAccount.value = BankAccount("1072-6644017", "AccNickname", BigDecimal("2174.63"), CubcCurrency.USD)
        transferType.value = TransferType.Cubc
    }
    val cubcTransferViewModel = CubcTransferViewModel(Application())

    CubcAppTheme() {
        CubcInputScreen(
            transferMainViewModel,
            cubcTransferViewModel,
        ) {}
    }
}