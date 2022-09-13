package com.tp.cubc.poc.account.repository

import com.mbanking.cubc.myAccount.repository.dataModel.QueryAccountInfoResponseBodyResult
import com.tp.cubc.poc.util.http.RemoteDataSourceUtil
import com.tp.cubc.poc.util.http.HttpRequestBody
import javax.inject.Inject

class AccountRemoteDataSource @Inject constructor(
    private val api: AccountApi
) {
    suspend fun queryAccountInfo(): Result<QueryAccountInfoResponseBodyResult> {
        return RemoteDataSourceUtil.wrapHttpResponse<QueryAccountInfoResponseBodyResult> {
            api.queryAccountInfo(HttpRequestBody("queryAccountInfo"))
        }
    }
}
