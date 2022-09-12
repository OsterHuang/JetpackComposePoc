package com.tp.cubc.poc.landing.repository.dataModel

import com.google.gson.annotations.SerializedName

// 未登入token
data class AccessTokenResponse(
    @SerializedName("accessToken")
    val accessToken: String = "",
)
