package com.tp.cubc.poc.util.http

import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tp.cubc.poc.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

val TOKEN_INVALID_CODES = setOf("M-9801", "M-9802", "M-9803")

class ResultErrorHandler(
    val coroutineScope: CoroutineScope,
    val result: Result<Any>,
    val showGlobalError: (result: Result<Any>) -> Unit,
    val showCommonError: (result: Result<Any>, resultErrorHandler: ResultErrorHandler) -> Unit,
) {
    companion object {
        var hasGlobalError = true
        var hasCommonError = true
    }
    var onDismissCommonError = {}

    suspend fun handleGlobalError(): ResultErrorHandler {
        if (result.isSuccess) return this

        when (val exception = result.exceptionOrNull()) {
            is ApiError -> {
                if (TOKEN_INVALID_CODES.contains(exception.msgCode)) {

                    coroutineScope.cancel()
                    showGlobalError(result)
                    delay(1000) // Waiting the cancellation to exit this coroutine job
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
                showCommonError(result, this)
                coroutineScope.cancel()
            }
            else -> {
                showCommonError(result, this)
                coroutineScope.cancel()
            }
        }

        return this
    }

    fun dismissCommonError() {
        onDismissCommonError()
    }
}

class ResultErrorHandlerProducer(
    private val showGlobalError: (result: Result<Any>) -> Unit,
    private val showCommonError: (result: Result<Any>, resultErrorHandler: ResultErrorHandler, ) -> Unit,
) {

    fun newHandler(
        coroutineScope: CoroutineScope,
        result: Result<Any>,
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
    var this_resultErrorHandler: ResultErrorHandler? by remember { mutableStateOf(null) }
    var result: Result<Any>? by remember { mutableStateOf(null) }

    // 目前global error 只有強制登出，如果未來使用enum ErrorTYpe來做不同的事情，或新增一個Error
    // 比如 isShowTokenInvalid, isShowSystemMaintaining, isShowVersionOut 之類的
    var isShowGlobalError by remember { mutableStateOf(false) }
    var isShowCommonError by remember { mutableStateOf(false) }

    val showGlobalError = fun (result: Result<Any>) {
        isShowGlobalError = true
    }
    val showCommonError = fun (
        result: Result<Any>,
        resultErrorHandler: ResultErrorHandler, // -> 此參數為dismiss時需要知道要callback哪個handler
    ) {
        this_resultErrorHandler = resultErrorHandler
        isShowCommonError = true
    }

    val errorHandlerProducer: ResultErrorHandlerProducer = ResultErrorHandlerProducer(
        showGlobalError,
        showCommonError,
    )

    if (isShowGlobalError) {
        GlobalErrorAlertDialog(
            transformMessage(result)
        ) {
            isShowGlobalError = false
            // Restart Activity
        }
    } else if (isShowCommonError) {
        CommonErrorAlertDialog(
            transformMessage(result)
        ) {
            isShowCommonError = false
            this_resultErrorHandler?.dismissCommonError()
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
        modifier = Modifier.width(300.dp),
        buttons = {
            Button(onClick = onDismissRequest) {
                Text("Confirm")
            }
        },
        onDismissRequest = onDismissRequest,
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


/**
 * 只有Composable可以使用stringResource
 * 所以取得錯誤訊息規劃到這邊
 * @see RemoteDataSourceUtil.wrapBody
 */
@Composable
fun transformMessage(result: Result<Any>?): String {
    return when (val exception = result?.exceptionOrNull()) {
        is UnknownHostException -> {
            stringResource(id = R.string.http_throwable_unknown_host)  + "(IOE)"
        }
        is IOException -> {
            stringResource(R.string.http_throwable_io) + "(IOE)"
        }
        is HttpException -> {
            stringResource(R.string.http_throwable_http) + "(HTTP)"
        }
        is ApiError -> {
            if (TOKEN_INVALID_CODES.contains(exception.msgCode)) {
                return exception.msgContent ?: "Invalid Token"
            }

            exception.msgContent ?: "Unknown Error"
        }
        null -> {
            "Unknown Error"
        }
        else -> {
            "Unexpected Error"
        }
    }
}
