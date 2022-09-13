package com.tp.cubc.poc.util.http

import com.tp.cubc.poc.util.constant.ApiReturnCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

object RemoteDataSourceUtil {
    suspend fun <RES_BODY_RESULT>wrapHttpResponse(
        block: suspend () -> Response<HttpResponseBody<RES_BODY_RESULT>>,
    ): Result<RES_BODY_RESULT> {
        return withContext(Dispatchers.IO) {
            try {
                val response = block.invoke()
                if (response.isSuccessful && response.body()?.msgCode == ApiReturnCode.Success.code) {
                    Result.success(
                        response.body()?.result ?: throw NullPointerException()
                    )
                } else {
                    if (response.body() != null) {
                        Result.failure(
                            ApiError(response.body()?.msgCode, response.body()?.msgContent)
                        )
                    } else {
                        Result.failure(
                            ApiError(response.code().toString(), ApiReturnCode.NoResponse.code)
                        )
                    }
                }
            } catch (throwable: Throwable) {
                Result.failure(throwable)
            }
        }
    }
}
