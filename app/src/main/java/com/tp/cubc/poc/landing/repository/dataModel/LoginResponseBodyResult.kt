package com.tp.cubc.poc.landing.repository.dataModel

import com.google.gson.annotations.SerializedName

data class LoginResponseBodyResult(
    @SerializedName("accessToken") // 登入後token
    val accessToken: String = "",
    @SerializedName("createCust") // 是否為非CUBC APP註冊之用戶首次登入
    val createCust: Boolean = false,
    @SerializedName("changeDevice") // 是否需換機
    val changeDevice: Boolean = false,
    /**
     * 額外使用的property非api spec
     */
    var errorCount: Int = 0
)
