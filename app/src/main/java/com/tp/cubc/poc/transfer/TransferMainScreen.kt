package com.tp.cubc.poc.transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.tp.cubc.poc.ui.bg.BasicBg
import com.tp.cubc.poc.ui.component.dropdown.DropdownField
import com.tp.cubc.poc.ui.component.TopBarTitleText
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import kotlinx.coroutines.launch


@Composable
fun TransferMainScreen(
    transferMainViewModel: TransferMainViewModel,
    transferTypeRouter: TransferTypeRouter
) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(true) { // Set true to execute on first recomposition
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

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DropdownField(
                    value = transferMainViewModel.fromAccount.value,
                    label = { Text("From Account") },
                    modifier = Modifier.fillMaxWidth(),
                    items = transferMainViewModel.accountList.value,
                    onValueChange = { transferMainViewModel.fromAccount.value = it }
                )
                transferMainViewModel.fromAccount.value?.run {
                    Text(
                        modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.End),
                        text = "Balance: ${transferMainViewModel.fromAccount.value?.balance}",
                        fontSize = 12.sp
                    )
                }
                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = transferMainViewModel.transferType.value?.name ?: "",
                    enabled = false,
                    label = { Text("Transfer To") },
                    onValueChange = {},
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "ArrowDropDown",
                            modifier = Modifier.rotate(180f),
                        )
                    },
                    modifier = Modifier.fillMaxWidth().clickable(enabled = true, onClick = transferTypeRouter.openTransferType)
                )
            }

            Spacer(Modifier.weight(1f))

            Button(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                onClick = {}
            ) {
                Text("Next")
            }
        }
    }
}


//@Preview(name = "phone", device = "spec:shape=Normal,width=375,height=790,unit=dp,dpi=480")
@Preview
@Composable
private fun PreviewScreen() {
    val navController = rememberNavController()
    CubcAppTheme() {
        TransferMainScreen(
            transferMainViewModel = viewModel(),
            TransferTypeRouter(navController)
        )
    }
}