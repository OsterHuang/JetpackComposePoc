package com.tp.cubc.poc.example.apierror

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tp.cubc.poc.app.CubcAppViewModel
import com.tp.cubc.poc.ui.bg.BasicBg
import com.tp.cubc.poc.ui.component.ErrorMessage
import com.tp.cubc.poc.ui.component.RoundedBorderColumn
import com.tp.cubc.poc.ui.component.TopBarTitleText
import com.tp.cubc.poc.util.http.ApiError
import com.tp.cubc.poc.util.http.CubcApiErrorCentral
import com.tp.cubc.poc.util.http.apiErrorMessage
import com.tp.cubc.poc.util.http.transformMessage
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

// Demo用，不符合框架規範，請勿拿來當新頁面的草本。
@OptIn(FlowPreview::class)
@Composable
fun ApiErrorDemoScreen(
    goHome: () -> Unit
) {
    BasicBg() {
        Column() {
            TopBarTitleText(text = "API ERROR Case Demo")

            CommonErrorArea(goHome)
        }
    }
}

@FlowPreview
@Composable
fun CommonErrorArea(
    goHome: () -> Unit
) {
    val appViewModel: CubcAppViewModel = viewModel(LocalContext.current as ComponentActivity)
    // 範例要呼API所以使用viewModel
    val apiErrorDemoViewModel: ApiErrorDemoViewModel = hiltViewModel()

    var value by remember{ mutableStateOf("") }
    var valueError by remember{ mutableStateOf("") }
    var alertMessage by remember{ mutableStateOf("") }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val onClickNoError = fun() {
        coroutineScope.launch {
            appViewModel.loading.value++
            val result = apiErrorDemoViewModel.inquiryNoError()
            appViewModel.loading.value--

            value = result.getOrNull()!!.result.name
        }
    }

    val errorHandlerProducer = CubcApiErrorCentral()
    val onClickGlobalError = fun() {
        coroutineScope.launch {
            appViewModel.loading.value++
            val result = apiErrorDemoViewModel.inquiryHasError()
            appViewModel.loading.value--

            errorHandlerProducer.newHandler(this, result)
                .handleGlobalError()

            if (result.isFailure) {
                alertMessage = result.apiErrorMessage()
                return@launch
            }

            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    val onClickCommonError = fun() {
        coroutineScope.launch {
            appViewModel.loading.value++
            val result = apiErrorDemoViewModel.inquiryHasError()
            appViewModel.loading.value--

            errorHandlerProducer.newHandler(this, result)
                .handleGlobalError()
                .handleCommonError()

            if (result.isSuccess)
                value = "API Successsssss...."
        }
    }

    val onClickInputRedBorderError = fun() {
        coroutineScope.launch {
            appViewModel.loading.value++
            val result = apiErrorDemoViewModel.inquiryHasError()
            appViewModel.loading.value--

            val errorHandler = errorHandlerProducer.newHandler(this, result)
                .handleGlobalError()

            if (result.isSuccess) {
                value = "API Successsssss...."
            } else {
                when (val exception = result.exceptionOrNull()) {
                    is ApiError -> {
                        if (exception.msgCode == "F-101") {
                            valueError = exception.msgContent ?: "未知的錯誤"
                        } else {
                            errorHandler.handleCommonError()
                        }
                    }
                }
            }
        }
    }

    val onClickToPageError = fun() {
        coroutineScope.launch {
            appViewModel.loading.value++
            val result = apiErrorDemoViewModel.inquiryHasError()
            appViewModel.loading.value--

            errorHandlerProducer.newHandler(this, result)
                .handleGlobalError()
                .handleCommonError(goHome)

            if (result.isSuccess)
                value = "如果失敗，可以回Home頁"
        }
    }

    RoundedBorderColumn() {
        Text("Common Error to Default Alert")
        OutlinedTextField(value = value, onValueChange = { value = it }, isError = valueError.isNotBlank())
        ErrorMessage(text = valueError)

        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceAround) {
            Button(onClick = onClickNoError ) { Text("No error") }
            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = onClickGlobalError ) { Text("Global Error Case || Customized Dialog") }
            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = onClickCommonError ) { Text("Common Error") }
            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = onClickInputRedBorderError ) { Text("Input Red Border") }
            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = onClickToPageError ) { Text("失敗去別頁…") }
        }
    }

    if (alertMessage.isNotBlank()) {
        AlertDialog(
            text = { Text(text = alertMessage) },
            onDismissRequest = { alertMessage = "" },
            confirmButton = {
                Button(onClick = { alertMessage = "" }) { Text("Confirm") }
            },
            dismissButton = {
                Button(onClick = { alertMessage = "" }) { Text("Cancel") }
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