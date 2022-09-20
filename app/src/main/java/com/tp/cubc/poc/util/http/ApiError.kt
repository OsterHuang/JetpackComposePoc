package com.tp.cubc.poc.util.http

import android.text.TextUtils

class ApiError(var msgCode: String?, var msgContent: String?) : Exception() {
    fun getFullErrorMsg(): String {
        return if (!TextUtils.isEmpty(msgCode)) {
            "$msgContent($msgCode)"
        } else {
            "$msgContent"
        }
    }
}

fun <T>Result<T>.apiErrorMessage(): String {
    return when (val exception = this.exceptionOrNull()) {
        is ApiError -> {
            exception.msgContent ?: "Unknown Error"
        }
        else -> {
            return "Unexpected Error"
        }
    }
}
