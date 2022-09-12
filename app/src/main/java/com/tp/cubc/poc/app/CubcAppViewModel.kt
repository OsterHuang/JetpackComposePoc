package com.tp.cubc.poc.app

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp.cubc.poc.TAG
import com.tp.cubc.poc.util.constant.CubcConstant.Companion.LOADING_PENDING_TIMEOUT
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CubcAppViewModel: ViewModel() {
    private val tag = TAG

    val isDarkModeState = mutableStateOf(false)
    val loading = mutableStateOf<Int>(0)

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
}