package com.tp.cubc.poc.util.http

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

val TOKEN_INVALID_CODES = setOf("M9801", "M9802", "M9803")

class ResultErrorHandler(
    val coroutineScope: CoroutineScope,
    val result: Result<HttpResponseBody<Any>>,
    val showGlobalError: (result: Result<HttpResponseBody<Any>>) -> Unit,
    val showCommonError: (result: Result<HttpResponseBody<Any>>, resultErrorHandler: ResultErrorHandler) -> Unit,
) {
    companion object {
        var hasGlobalError = true
        var hasCommonError = true
    }
    var onDismissCommonError = {}

    fun handleGlobalError(): ResultErrorHandler {
        if (result.isSuccess) return this

        when (val exception = result.exceptionOrNull()) {
            is ApiError -> {
                if (TOKEN_INVALID_CODES.contains(exception.msgCode)) {
                    showGlobalError(result)
                    coroutineScope.cancel()
                }
            }
        }

        return this
    }

    fun handleCommonError(onDismiss: () -> Unit = {}): ResultErrorHandler {
        if (result.isSuccess) return this

        onDismissCommonError = onDismiss

        when (val exception = result.exceptionOrNull()) {
            is ApiError -> {
                if (TOKEN_INVALID_CODES.contains(exception.msgCode)) return this
                coroutineScope.cancel()
            }
            else -> {
                showCommonError(result, this)
                onDismissCommonError = onDismiss
                coroutineScope.cancel()
            }
        }

        return this
    }

    fun onDismissCommonError() {
        onDismissCommonError()
    }
}

class ResultErrorHandlerProducer(
    val showGlobalError: (result: Result<HttpResponseBody<Any>>) -> Unit,
    val showCommonError: (result: Result<HttpResponseBody<Any>>, resultErrorHandler: ResultErrorHandler, ) -> Unit,
) {

    fun newHandler(
        coroutineScope: CoroutineScope,
        result: Result<HttpResponseBody<Any>>,
    ): ResultErrorHandler {
        return ResultErrorHandler(
            coroutineScope = coroutineScope,
            result = result,
            // -- 以下為 CubcApiErrorCentral 的溝通 --
            showGlobalError = showGlobalError,
            showCommonError = showCommonError,
        )
    }

}

@Composable
fun CubcApiErrorCentral(): ResultErrorHandlerProducer {
    var resultErrorHandler: ResultErrorHandler? by remember { mutableStateOf(null) }
    var result: Result<HttpResponseBody<Any>>? by remember { mutableStateOf(null) }

    // 目前global error 只有強制登出，如果未來使用enum ErrorTYpe來做不同的事情，或新增一個Error
    // 比如 isShowTokenInvalid, isShowSystemMaintaining, isShowVersionOut 之類的
    var isShowGlobalError by remember { mutableStateOf(false) }
    var isShowCommonError by remember { mutableStateOf(false) }

    val showGlobalError = fun (result: Result<HttpResponseBody<Any>>) {
        isShowGlobalError = true
    }
    val showCommonError = fun (
        result: Result<HttpResponseBody<Any>>,
        resultErrorHandler: ResultErrorHandler, // -> 此參數為dismiss時需要知道要callback哪個handler
    ) {
        resultErrorHandler.onDismissCommonError()
        isShowCommonError = true
    }

    lateinit var errorHandlerProducer: ResultErrorHandlerProducer
    LaunchedEffect(true) {
        errorHandlerProducer = ResultErrorHandlerProducer(
            showGlobalError,
            showCommonError,
        )
    }

    if (isShowGlobalError) {
        GlobalErrorAlertDialog(
            result?.exceptionOrNull()?.message ?: "Token Invalid"
        ) {
            isShowGlobalError = false
            // Restart Activity
        }
    } else if (isShowCommonError) {
        CommonErrorAlertDialog(
            result?.exceptionOrNull()?.message ?: "Unknown Error"
        ) {
            isShowCommonError = false
            resultErrorHandler?.onDismissCommonError()
            // Restart Activity
        }
    }


    return errorHandlerProducer!!
}

@Composable
fun GlobalErrorAlertDialog(
    text: String,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        title = { Text("即將被登出") },
        text = { Text(text) },
        onDismissRequest = onDismissRequest,
        buttons = {
            Button(onClick = onDismissRequest) {
                Text("Confirm")
            }
        }
    )
}

@Composable
fun CommonErrorAlertDialog(
    text: String,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        text = { Text(text) },
        onDismissRequest = onDismissRequest,
        buttons = {
            Button(onClick = onDismissRequest) {
                Text("Confirm")
            }
        }
    )
}
