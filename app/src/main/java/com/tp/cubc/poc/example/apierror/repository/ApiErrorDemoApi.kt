package com.tp.cubc.poc.landing.repository

import com.mbanking.cubc.login.repository.dataModel.LoginRequestBody
import com.tp.cubc.poc.landing.repository.dataModel.AccessTokenResponse
import com.tp.cubc.poc.landing.repository.dataModel.LoginResponseBodyResult
import com.tp.cubc.poc.util.http.HttpRequestBody
import com.tp.cubc.poc.util.http.HttpResponseBody
import com.tp.cubc.poc.util.http.HttpResponseBodyResultEmpty
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiErrorDemoApi {
    // 未登入取得Token
    @POST("error-sample/no-error")
    suspend fun noError(
        @Body requestBody: HttpRequestBody
    ): Response<HttpResponseBody<HttpResponseBodyResultEmpty>>


    @POST("error-sample/has-error")
    suspend fun hasError(
        @Body requestBody: HttpRequestBody
    ): Response<HttpResponseBody<HttpResponseBodyResultEmpty>>
}
