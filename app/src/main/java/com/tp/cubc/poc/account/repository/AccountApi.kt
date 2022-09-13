package com.tp.cubc.poc.account.repository

import com.mbanking.cubc.myAccount.repository.dataModel.QueryAccountInfoResponseBodyResult
import com.tp.cubc.poc.util.http.HttpRequestBody
import com.tp.cubc.poc.util.http.HttpResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountApi {
    @POST("api/account/queryAccountInfo")
    suspend fun queryAccountInfo(
        @Body requestBody: HttpRequestBody
    ): Response<HttpResponseBody<QueryAccountInfoResponseBodyResult>>
}
