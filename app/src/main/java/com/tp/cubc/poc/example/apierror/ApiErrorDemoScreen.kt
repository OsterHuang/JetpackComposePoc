package com.tp.cubc.poc.example

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tp.cubc.poc.app.CubcAppViewModel
import com.tp.cubc.poc.example.apierror.ApiErrorDemoViewModel
import com.tp.cubc.poc.ui.bg.BasicBg
import com.tp.cubc.poc.ui.component.RoundedBorderColumn
import com.tp.cubc.poc.ui.component.TopBarTitleText
import com.tp.cubc.poc.util.http.CubcApiErrorCentral
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

// Demo用，不符合框架規範，請勿拿來當新頁面的草本。
@OptIn(FlowPreview::class)
@Composable
fun ApiErrorDemoScreen() {
    BasicBg() {
        Column() {
            TopBarTitleText(text = "API ERROR Case Demo")

            CommonErrorArea()
        }
    }
}

@FlowPreview
@Composable
fun CommonErrorArea() {
    val appViewModel: CubcAppViewModel = viewModel(LocalContext.current as ComponentActivity)
    // 範例要呼API所以使用viewModel
    val apiErrorDemoViewModel: ApiErrorDemoViewModel = hiltViewModel()

    var value by remember{ mutableStateOf("") }
    var valueError by remember{ mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    val onClickNoError = fun() {
        coroutineScope.launch {
            appViewModel.loading.value++
            val result = apiErrorDemoViewModel.inquiryNoError()
            appViewModel.loading.value--

            value = result.getOrNull()!!.result.name
        }
    }

    val onClickHasError = fun() {
        coroutineScope.launch {
            appViewModel.loading.value++
            val result = apiErrorDemoViewModel.inquiryHasError()
            appViewModel.loading.value--

            valueError = result.exceptionOrNull()!!.message ?: "Unknown Error"
        }
    }

    val errorHandlerProducer = CubcApiErrorCentral()
    val onClickGlobalError = fun() {
        coroutineScope.launch {
            appViewModel.loading.value++
            val result = apiErrorDemoViewModel.inquiryHasError()
            appViewModel.loading.value--

            errorHandlerProducer.newHandler(coroutineScope, result)
                .handleCommonError(onClickNoError)
                .handleCommonError()

            valueError = result.exceptionOrNull()!!.message ?: "Unknown Error"
        }
    }

    RoundedBorderColumn() {
        Text("Common Error to Default Alert")
        TextField(value = value, onValueChange = { value = it })
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Button(modifier = Modifier.weight(1f), onClick = onClickNoError ) { Text("Global Error Case") }
            Spacer(modifier = Modifier.width(10.dp))
            Button(modifier = Modifier.weight(1f), onClick = onClickGlobalError ) { Text("Common Err Callback") }
        }

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Button(modifier = Modifier.weight(1f), onClick = {} ) { Text("No Error") }
            Spacer(modifier = Modifier.width(10.dp))
            Button(modifier = Modifier.weight(1f), onClick = {} ) { Text("Common Error") }
        }

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Button(modifier = Modifier.weight(1f), onClick = {} ) { Text("Customized Error Dialog") }
            Spacer(modifier = Modifier.width(10.dp))
            Button(modifier = Modifier.weight(1f), onClick = {} ) { Text("Red border") }
        }
    }

    if (value.isNotBlank() || valueError.isNotBlank()) {
        AlertDialog(
            text = { Text(text = value + valueError) },
            onDismissRequest = { valueError = ""; value = "" },
            confirmButton = {
                Button(onClick = { valueError = ""; value = "" }) { Text("Confirm") }
            },
            dismissButton = {
                Button(onClick = { valueError = ""; value = "" }) { Text("Cancel") }
            },
        )
    }
}

//@Preview
//@Composable
//fun PreviewApiErrorDemoScreen() {
//    CubcAppTheme() {
//        ApiErrorDemoScreen()
//    }
//}