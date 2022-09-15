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
import com.mbanking.cubc.myAccount.repository.dataModel.MyBankAccount
import com.mbanking.cubc.myAccount.repository.dataModel.QueryAccountInfoResponseBodyResult
import com.tp.cubc.poc.account.repository.AccountApi
import com.tp.cubc.poc.account.repository.AccountRemoteDataSource
import com.tp.cubc.poc.app.CubcAppViewModel
import com.tp.cubc.poc.transfer.dataModel.BankAccount
import com.tp.cubc.poc.transfer.dataModel.OtherBank
import com.tp.cubc.poc.transfer.dataModel.TransferType
import com.tp.cubc.poc.ui.component.RoundedBorderColumn
import com.tp.cubc.poc.ui.component.dropdown.DropdownField
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import com.tp.cubc.poc.util.constant.CubcCurrency
import com.tp.cubc.poc.util.http.HttpRequestBody
import com.tp.cubc.poc.util.http.HttpResponseBody
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import java.math.BigDecimal


@Composable
fun TransferMainTopRegion(
    accountList: List<MyBankAccount>,
    fromAccount: MyBankAccount?,
    transferToBank: OtherBank?,
    transferType: TransferType?,
    transferTypeRouter: TransferTypeRouter? = null, // 只有TransferMain需要開啟轉帳類型Dialog
) {
    // Computed Values
    val txtTransferTo = transferToBank?.name ?: transferType?.name ?: ""
    val txtTransferType = transferToBank?.run {
        transferType?.name ?: " -- "
    } ?: ""

    DropdownField(
        value = fromAccount,
        label = { Text("From Account") },
        modifier = Modifier.fillMaxWidth(),
        items = accountList,
        onValueChange = { fromAccount }
    )
    fromAccount?.run {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            text = "Balance: ${getCurrency().symbol}${balance}",
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

    val accountList = listOf(
        MyBankAccount("811", "01110110335101", CubcCurrency.USD.name, BigDecimal("2007.15"), "My e-account USD"),
        MyBankAccount("811", "800443559", CubcCurrency.KHR.name, BigDecimal("300000"), "My e-account USD"),
    )
    val fromAccount = accountList[1]
    val transferToBank = OtherBank("台北富邦")


    CubcAppTheme() {
        RoundedBorderColumn {
            TransferMainTopRegion(
                accountList = accountList,
                fromAccount = fromAccount,
                transferToBank = transferToBank ,
                transferType = TransferType.Fast,
                transferTypeRouter = TransferTypeRouter(navController)
            )
        }
    }
}