package com.tp.cubc.poc.app

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp.cubc.poc.MainApplication
import com.tp.cubc.poc.TAG
import com.tp.cubc.poc.landing.repository.LandingRemoteDataSource
import com.tp.cubc.poc.landing.repository.dataModel.AccessTokenResponse
import com.tp.cubc.poc.util.constant.CubcConstant.Companion.LOADING_PENDING_TIMEOUT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@HiltViewModel
class CubcAppViewModel @Inject constructor(
    private val dataSource: LandingRemoteDataSource
): ViewModel() {
    private val tag = TAG

    val isDarkModeState = mutableStateOf(false)
    val loading = mutableStateOf(0)

    init {
        viewModelScope.launch {
            /*
             * 如果loading有人沒有關閉，則時間到後自動關閉
             */
            snapshotFlow { loading.value }
                .filter { it != 0 }
                .debounce(LOADING_PENDING_TIMEOUT)
                .collect {
                    Log.d(tag, "loading count: $it")
                    loading.value = 0
                }
        }
    }
    
    suspend fun requireAccessToken() {
        val result: Result<AccessTokenResponse> = dataSource.accessToken()
        MainApplication.instance.cubcAppData.token = result.getOrNull()?.accessToken
    }
}
