package com.tp.cubc.poc.landing.repository

import com.mbanking.cubc.myAccount.repository.dataModel.NoErrorResponseBodyResult
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
    ): Response<HttpResponseBody<NoErrorResponseBodyResult>>


    @POST("error-sample/has-error")
    suspend fun hasError(
        @Body requestBody: HttpRequestBody
    ): Response<HttpResponseBody<HttpResponseBodyResultEmpty>>
}
