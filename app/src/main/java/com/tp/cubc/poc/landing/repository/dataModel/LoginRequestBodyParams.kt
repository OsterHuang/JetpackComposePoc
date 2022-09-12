package com.mbanking.cubc.login.repository.dataModel

import com.google.gson.annotations.SerializedName

class LoginRequestBodyParams(
    @SerializedName("userName") // 使用者代號
    private var userName: String = "",
    @SerializedName("userMima") // 使用者密碼
    private var userMima: String = ""
)
