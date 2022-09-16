package com.tp.cubc.poc.landing.repository

import com.tp.cubc.poc.util.http.HttpRequestBody
import com.tp.cubc.poc.util.http.HttpResponseBody
import com.tp.cubc.poc.util.http.HttpResponseBodyResultEmpty
import com.tp.cubc.poc.util.http.RemoteDataSourceUtil
import javax.inject.Inject

class ApiErrorDemoRemoteDataSource @Inject constructor(
    private val api: ApiErrorDemoApi
) {
    suspend fun inquiryNoError(): Result<HttpResponseBody<HttpResponseBodyResultEmpty>> {
        return RemoteDataSourceUtil.wrapBody {
            api.noError(HttpRequestBody("inquiryNoError"))
        }
    }

    suspend fun inquiryHasError(): Result<HttpResponseBody<HttpResponseBodyResultEmpty>> {
        return RemoteDataSourceUtil.wrapBody {
            api.noError(HttpRequestBody("inquiryHasError"))
        }
    }
}
