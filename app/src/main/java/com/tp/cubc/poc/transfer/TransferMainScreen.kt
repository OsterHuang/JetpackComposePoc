package com.tp.cubc.poc.transfer

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.mbanking.cubc.myAccount.repository.dataModel.MyBankAccount
import com.mbanking.cubc.myAccount.repository.dataModel.QueryAccountInfoResponseBodyResult
import com.tp.cubc.poc.account.repository.AccountApi
import com.tp.cubc.poc.account.repository.AccountRemoteDataSource
import com.tp.cubc.poc.app.CubcAppViewModel
import com.tp.cubc.poc.transfer.dataModel.OtherBank
import com.tp.cubc.poc.transfer.dataModel.TransferType
import com.tp.cubc.poc.ui.bg.BasicBg
import com.tp.cubc.poc.ui.component.BottomButtonArea
import com.tp.cubc.poc.ui.component.RoundedBorderColumn
import com.tp.cubc.poc.ui.component.TopBarTitleText
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import com.tp.cubc.poc.util.constant.CubcCurrency
import com.tp.cubc.poc.util.http.HttpRequestBody
import com.tp.cubc.poc.util.http.HttpResponseBody
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import retrofit2.Response
import java.math.BigDecimal

@Composable
fun TransferMainScreenAdapter(
    transferMainViewModel: TransferMainViewModel,
    transferTypeRouter: TransferTypeRouter,
) {
    val appViewModel: CubcAppViewModel = viewModel(LocalContext.current as ComponentActivity)

    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(transferMainViewModel.toString()) { // Set true to execute on first recompositio
        coroutineScope.launch {
            appViewModel.loading.value++
            transferMainViewModel.queryAccountList()
            appViewModel.loading.value--
        }

        transferMainViewModel.transferToBank.value = null
        transferMainViewModel.transferType.value = null
    }

    TransferMainScreen(
        accountList = transferMainViewModel.accountList.value,
        fromAccount = transferMainViewModel.fromAccount.value,
        transferToBank = transferMainViewModel.transferToBank.value,
        transferType = transferMainViewModel.transferType.value,
        transferTypeRouter = transferTypeRouter,
    )
}

@Composable
fun TransferMainScreen(
    accountList: List<MyBankAccount>,
    fromAccount: MyBankAccount?,
    transferToBank: OtherBank?,
    transferType: TransferType?,
    transferTypeRouter: TransferTypeRouter? = null, // 只有TransferMain需要開啟轉帳類型Dialog
){
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
                    accountList,
                    fromAccount,
                    transferToBank,
                    transferType,
                    transferTypeRouter
                )
                Spacer(Modifier.height(12.dp))
            }

            Spacer(Modifier.weight(1f))

            BottomButtonArea {
                Button(
                    modifier = Modifier.fillMaxWidth().height(42.dp),
                    enabled =  false,
                    onClick = {}
                ) {
                    Text("Next")
                }
            }
        }
    }
}

//@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Preview
@Composable
private fun PreviewScreen() {
    val navController = rememberNavController()

    val accountList = listOf(
        MyBankAccount("811", "01110110335101", CubcCurrency.USD.name, BigDecimal("2007.15"), "My e-account USD"),
        MyBankAccount("811", "800443559", CubcCurrency.KHR.name, BigDecimal("300000"), "MY KHR E-acc"),
    )
    val fromAccount = accountList[1]
    val transferToBank = OtherBank("台北富邦")

    CubcAppTheme() {
        TransferMainScreen(
            accountList = accountList,
            fromAccount = fromAccount,
            transferToBank = transferToBank,
            transferType = TransferType.Bakong,
            transferTypeRouter = TransferTypeRouter(navController),
        )
    }
}