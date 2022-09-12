package com.mbanking.cubc.login.repository.dataModel

import com.google.gson.annotations.SerializedName
import com.tp.cubc.poc.util.http.HttpRequestBody

class LoginRequestBody(
    @SerializedName("params")
    var params: LoginRequestBodyParams
) : HttpRequestBody("login")
