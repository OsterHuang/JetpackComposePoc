package com.tp.cubc.poc.util.http

import com.google.gson.annotations.SerializedName

data class HttpResponseBody<BODY_RESULT>(
    @SerializedName("msgCode")
    val msgCode: String = "",
    @SerializedName("msgContent")
    val msgContent: String = "",
    @SerializedName("result")
    val result: BODY_RESULT
)
