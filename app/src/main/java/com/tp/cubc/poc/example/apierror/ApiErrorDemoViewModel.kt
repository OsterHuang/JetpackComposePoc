package com.tp.cubc.poc.example.apierror

import androidx.lifecycle.ViewModel
import com.mbanking.cubc.myAccount.repository.dataModel.NoErrorResponseBodyResult
import com.tp.cubc.poc.TAG
import com.tp.cubc.poc.landing.repository.ApiErrorDemoRemoteDataSource
import com.tp.cubc.poc.util.http.HttpResponseBody
import com.tp.cubc.poc.util.http.HttpResponseBodyResultEmpty
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ApiErrorDemoViewModel @Inject constructor(
    private val dataSource: ApiErrorDemoRemoteDataSource
): ViewModel() {
    private val tag = TAG

    suspend fun inquiryNoError(): Result<HttpResponseBody<NoErrorResponseBodyResult>> {
        return dataSource.inquiryNoError()
    }

    suspend fun inquiryHasError(): Result<HttpResponseBody<HttpResponseBodyResultEmpty>> {
        return dataSource.inquiryHasError()
    }
}