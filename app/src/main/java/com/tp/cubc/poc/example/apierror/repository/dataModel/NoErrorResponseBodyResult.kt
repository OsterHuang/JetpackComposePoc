package com.mbanking.cubc.myAccount.repository.dataModel

import com.google.gson.annotations.SerializedName

data class NoErrorResponseBodyResult(
    @SerializedName("name") // 帳戶分行代碼
    val name: String = "",
)
