package com.tp.cubc.poc.util.http

import android.annotation.SuppressLint
import com.tp.cubc.poc.MainApplication
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
open class HttpRequestBody(val apiId: String) {
    val txDateTime: String
    val deviceUUID: String
    init {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        txDateTime = sdf.format(Calendar.getInstance().time)

        deviceUUID = MainApplication.instance.cubcAppData.deviceUUID ?: "no-device-id"
    }
}
