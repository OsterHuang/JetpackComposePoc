package com.tp.cubc.poc.landing.repository

import com.mbanking.cubc.login.repository.dataModel.LoginRequestBody
import com.mbanking.cubc.login.repository.dataModel.LoginRequestBodyParams
import com.tp.cubc.poc.landing.repository.dataModel.AccessTokenResponse
import com.tp.cubc.poc.landing.repository.dataModel.LoginResponseBodyResult
import com.tp.cubc.poc.util.http.RemoteDataSourceUtil
import com.tp.cubc.poc.util.http.HttpRequestBody
import com.tp.cubc.poc.util.http.HttpResponseBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class LandingRemoteDataSource @Inject constructor(
    private val api: LandingApi
) {
    suspend fun accessToken(): Result<AccessTokenResponse> {
        return RemoteDataSourceUtil.wrapHttpResponse<AccessTokenResponse> {
            api.accessToken(HttpRequestBody("accessToken"))
        }
    }

    suspend fun login(userName: String, userMima: String): Result<LoginResponseBodyResult> {
        return RemoteDataSourceUtil.wrapHttpResponse<LoginResponseBodyResult> {
            api.login(LoginRequestBody(LoginRequestBodyParams(userName, userMima)))
        }
    }
}
