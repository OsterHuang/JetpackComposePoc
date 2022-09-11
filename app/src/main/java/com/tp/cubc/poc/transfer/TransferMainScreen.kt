package com.tp.cubc.poc.transfer

import android.app.Application
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
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.ui.bg.BasicBg
import com.tp.cubc.poc.ui.component.BottomButtonArea
import com.tp.cubc.poc.ui.component.RoundedBorderColumn
import com.tp.cubc.poc.ui.component.TopBarTitleText
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import kotlinx.coroutines.launch


@Composable
fun TransferMainScreen(
    transferMainViewModel: TransferMainViewModel,
    transferTypeRouter: TransferTypeRouter,
) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(true) { // Set true to execute on first recompositio
        coroutineScope.launch {
            transferMainViewModel.queryAccountList()
        }
        transferMainViewModel.transferToBank.value = null
        transferMainViewModel.transferType.value = null
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
                    transferMainViewModel = transferMainViewModel,
                    transferTypeRouter = transferTypeRouter,
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
    val transferMainViewModel = TransferMainViewModel(Application())
    CubcAppTheme() {
        TransferMainScreen(
            transferMainViewModel,
            TransferTypeRouter(navController),
        )
    }
}