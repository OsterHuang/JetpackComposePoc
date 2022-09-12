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
