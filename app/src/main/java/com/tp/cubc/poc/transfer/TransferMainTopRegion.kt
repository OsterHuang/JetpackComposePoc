package com.tp.cubc.poc.transfer

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.app.CubcAppViewModel
import com.tp.cubc.poc.ui.component.RoundedBorderColumn
import com.tp.cubc.poc.ui.component.dropdown.DropdownField
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun TransferMainTopRegion(
    transferMainViewModel: TransferMainViewModel,
    transferTypeRouter: TransferTypeRouter? = null, // 只有TransferMain需要開啟轉帳類型Dialog
) {
    val appViewModel: CubcAppViewModel = viewModel(LocalContext.current as ComponentActivity)

    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(transferMainViewModel.toString()) { // Set true to execute on first recompositio
        coroutineScope.launch {
            appViewModel.loading.value++
            transferMainViewModel.queryAccountList()
            appViewModel.loading.value--
        }
    }

    // Computed Values
    val txtTransferTo = transferMainViewModel.transferToBank.value?.name ?:
        transferMainViewModel.transferType.value?.name ?: ""
    val txtTransferType = transferMainViewModel.transferToBank.value?.run {
        transferMainViewModel.transferType.value?.name ?: " -- "
    } ?: ""

    DropdownField(
        value = transferMainViewModel.fromAccount.value,
        label = { Text("From Account") },
        modifier = Modifier.fillMaxWidth(),
        items = transferMainViewModel.accountList.value,
        onValueChange = { transferMainViewModel.fromAccount.value = it }
    )
    transferMainViewModel.fromAccount.value?.run {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            text = "Balance: ${currency.symbol}${balance}",
            fontSize = 12.sp
        )
    }
    Spacer(Modifier.height(12.dp))

    OutlinedTextField(
        value = txtTransferTo,
        enabled = false,
        label = { Text("Transfer To") },
        placeholder = { Text("Please Select") },
        onValueChange = {},
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "ArrowDropDown",
                modifier = Modifier.rotate(180f),
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = transferTypeRouter != null, onClick = {
                transferTypeRouter?.goOpenTransferType?.invoke()
            })
    )

    if (txtTransferType.isNotBlank()) {
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = txtTransferType,
            enabled = false,
            label = { Text("Transfer Type") },
            onValueChange = {},
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "ArrowDropDown",
                    modifier = Modifier.rotate(180f),
                )
            },
            modifier = Modifier .fillMaxWidth()
        )
    }
}


//@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Preview
@Composable
private fun PreviewScreen() {
    val navController = rememberNavController()
    val transferMainViewModel = TransferMainViewModel(Application())
    CubcAppTheme() {
        RoundedBorderColumn {
            TransferMainTopRegion(

                transferMainViewModel = transferMainViewModel,
                transferTypeRouter = TransferTypeRouter(navController),
            )
        }
    }
}