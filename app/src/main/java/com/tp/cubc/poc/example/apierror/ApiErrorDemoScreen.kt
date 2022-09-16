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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tp.cubc.poc.app.CubcAppViewModel
import com.tp.cubc.poc.example.apierror.ApiErrorDemoViewModel
import com.tp.cubc.poc.ui.bg.BasicBg
import com.tp.cubc.poc.ui.component.*
import com.tp.cubc.poc.ui.theme.CubcAppTheme
import kotlinx.coroutines.launch

// Demo用，不符合框架規範，請勿拿來當新頁面的草本。
@Composable
fun ApiErrorDemoScreen() {
    BasicBg() {
        Column() {
            TopBarTitleText(text = "API ERROR Case Demo")

            CommonErrorArea()
            CustomErrorArea()
            RedBorderErrorArea()
        }
    }
}

@Composable
fun CommonErrorArea() {
    val appViewModel: CubcAppViewModel = viewModel(LocalContext.current as ComponentActivity)
    val apiErrorDemoViewModel: ApiErrorDemoViewModel = hiltViewModel()

    var value by remember{ mutableStateOf("") }
    var valueError by remember{ mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    val onClickNoError = fun() {
        coroutineScope.launch {
            appViewModel.loading.value++
            apiErrorDemoViewModel.inquiryNoError()
            appViewModel.loading.value--
        }
    }

    RoundedBorderColumn() {
        Text("Common Error to Default Alert")
        TextField(value = value, onValueChange = { value })
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Button(modifier = Modifier.weight(1f), onClick = onClickNoError ) { Text("API No Error") }
            Spacer(modifier = Modifier.width(10.dp))
            Button(modifier = Modifier.weight(1f), onClick = { /*TODO*/ }) { Text("API Error") }
        }
        ErrorMessage(text = valueError)
    }

    if (valueError.isNotBlank()) {
        AlertDialog(
            text = { Text(text = valueError) },
            onDismissRequest = { valueError = "" },
            confirmButton = {
                Button(onClick = { valueError = "" }) { Text("Confirm") }
            },
            dismissButton = {
                Button(onClick = { valueError = "" }) { Text("Cancel") }
            },
        )
    }
}

@Composable
fun CustomErrorArea() {
    var value by remember{ mutableStateOf("") }
    var valueError by remember{ mutableStateOf("") }

    RoundedBorderColumn() {
        Text("Customized Error to Custom Alert")
        TextField(value = value, onValueChange = { value })
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Button(modifier = Modifier.weight(1f), onClick = { /*TODO*/ }) { Text("API No Error") }
            Spacer(modifier = Modifier.width(10.dp))
            Button(modifier = Modifier.weight(1f), onClick = { /*TODO*/ }) { Text("API Error") }
        }
        ErrorMessage(text = valueError)
    }
}

@Composable
fun RedBorderErrorArea() {
    var value by remember{ mutableStateOf("") }
    var valueError by remember{ mutableStateOf("") }

    RoundedBorderColumn() {
        Text("Red border Error to Custom Alert")
        TextField(value = value, onValueChange = { value })
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Button(modifier = Modifier.weight(1f), onClick = { /*TODO*/ }) { Text("API No Error") }
            Spacer(modifier = Modifier.width(10.dp))
            Button(modifier = Modifier.weight(1f), onClick = { /*TODO*/ }) { Text("API Error") }
        }
        ErrorMessage(text = valueError)
    }
}

@Preview
@Composable
fun PreviewApiErrorDemoScreen() {
    CubcAppTheme() {
        ApiErrorDemoScreen()
    }
}