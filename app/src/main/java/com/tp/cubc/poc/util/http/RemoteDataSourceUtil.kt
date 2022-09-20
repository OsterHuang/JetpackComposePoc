package com.tp.cubc.poc.util.http

import androidx.compose.ui.res.stringResource
import com.tp.cubc.poc.R
import com.tp.cubc.poc.util.constant.ApiReturnCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

object RemoteDataSourceUtil {
    /**
     * 呼叫API並且將Response轉換成Result
     * 所有error, exception都轉成 ApiError
     */
    suspend fun <RES_BODY>wrapBody(
        block: suspend () -> Response<HttpResponseBody<RES_BODY>>,
    ): Result<HttpResponseBody<RES_BODY>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = block.invoke()

                if (response.isSuccessful && response.body() != null && response.body()?.msgCode == ApiReturnCode.Success.code) {
                    Result.success(
                        response.body()!!  // Already pre-check at the if condition
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

    /**
     * 呼叫API並且將Response轉換成Result
     */
    suspend fun <RES_BODY_RESULT>wrapBodyResult(
        block: suspend () -> Response<HttpResponseBody<RES_BODY_RESULT>>,
    ): Result<RES_BODY_RESULT> {
        return withContext(Dispatchers.IO) {
            try {
                val response = block.invoke()
                if (response.isSuccessful && response.body() != null && response.body()?.msgCode == ApiReturnCode.Success.code) {
                    Result.success(
                        response.body()!!.result // Already pre-check at the if condition
                    )
                } else {
                    if (response.body() != null) {
                        Result.failure(
                            ApiError(response.body()?.msgCode, response.body()?.msgContent)
                        )
                    } else {
                        Result.failure(
                            ApiError(response.code().toString(), ApiReturnCode.NoResponseBody.code)
                        )
                    }
                }
            } catch (throwable: Throwable) {
                Result.failure(throwable)
            }
        }
    }

}
