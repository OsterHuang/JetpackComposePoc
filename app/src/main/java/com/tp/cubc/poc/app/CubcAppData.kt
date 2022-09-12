package com.tp.cubc.poc.app

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CubcAppData @Inject constructor() {

    var token: String? = null
    var deviceUUID: String? = null
    // 中台設定
    val systemConfig: MutableMap<String, String> = mutableMapOf()


}