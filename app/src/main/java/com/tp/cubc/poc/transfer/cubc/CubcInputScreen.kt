package com.tp.cubc.poc.transfer.cubc

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.R
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
import com.tp.cubc.poc.ui.theme.Green500
import com.tp.cubc.poc.util.CubcCurrency
import kotlinx.coroutines.launch
import java.math.BigDecimal

@Composable
fun CubcInputScreen(
    transferMainViewModel: TransferMainViewModel,
    goConfirm: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(true) { // Set true to execute on first recompositio
        coroutineScope.launch {
            transferMainViewModel.queryAccountList()
        }
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
                    value = "",
                    label = { Text("Beneficiary Account Number") },
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {}
                )
                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = "",
                    label = { Text("Transfer Amount") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = { Text("USD") },
                    onValueChange = {}
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = "",
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

                DropdownField<DropdownItemSelectable>(
                    value = object : DropdownItemSelectable {
                        override fun getLabel(): String {
                            return "Pay for Goods"
                        }
                    },
                    label = { Text("Purpose") },
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {}
                )
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
private fun PreviewScreen() {
    val transferMainViewModel = TransferMainViewModel(Application()).apply {
        fromAccount.value = BankAccount("1072-6644017", BigDecimal("2174.63"), CubcCurrency.USD)
        transferType.value = TransferType.Cubc
    }

    CubcAppTheme() {
        CubcInputScreen(
            transferMainViewModel,
        ) {}
    }
}