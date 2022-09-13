package com.tp.cubc.poc.util.http

import com.tp.cubc.poc.MainApplication
import okhttp3.Interceptor
import okhttp3.Response

class HttpHeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accept", "application/json;charset=utf-8")
            .addHeader("Authorization", MainApplication.instance.cubcAppData.token ?: "")
            .build()
        return chain.proceed(request)
    }
}