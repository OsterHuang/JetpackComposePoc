package com.tp.cubc.poc.transfer.bakongwallet

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.transfer.TransferMainTopRegion
import com.tp.cubc.poc.transfer.TransferMainViewModel
import com.tp.cubc.poc.transfer.model.BankAccount
import com.tp.cubc.poc.transfer.model.TransferType
import com.tp.cubc.poc.ui.bg.BasicBg
import com.tp.cubc.poc.ui.component.BottomButtonArea
import com.tp.cubc.poc.ui.component.RoundedBorderColumn
import com.tp.cubc.poc.ui.component.TopBarTitleText
import com.tp.cubc.poc.ui.component.dropdown.DropdownField
import com.tp.cubc.poc.ui.component.dropdown.DropdownItemSelectable
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import com.tp.cubc.poc.util.CubcCurrency
import kotlinx.coroutines.launch
import java.math.BigDecimal

@Composable
fun BakongWalletInputScreen(
    transferMainViewModel: TransferMainViewModel,
    goConfirm: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(true) { // Set true to execute on first recompositio
        coroutineScope.launch {
            transferMainViewModel.queryAccountList()
        }
    }

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
                    transferMainViewModel = transferMainViewModel
                )
                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = "",
                    label = { Text("Bakong Account") },
                    leadingIcon = { Text("+855") },
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
                    modifier = Modifier.fillMaxWidth(),
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
    val transferMainViewModel = TransferMainViewModel(Application()).apply {
        fromAccount.value = BankAccount("1072-6644017", BigDecimal("2174.63"), CubcCurrency.USD)
        transferType.value = TransferType.Bakong
    }

    CubcAppTheme() {
        BakongWalletInputScreen(
            transferMainViewModel,
        ) {}
    }
}