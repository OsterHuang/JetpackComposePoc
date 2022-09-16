package com.tp.cubc.poc.landing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tp.cubc.poc.MainApplication
import com.tp.cubc.poc.TAG
import com.tp.cubc.poc.landing.repository.LandingRemoteDataSource
import com.tp.cubc.poc.landing.repository.dataModel.LoginResponseBodyResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val landingRemoteDataSource: LandingRemoteDataSource
): ViewModel() {
    private val tag = TAG

    var username by mutableStateOf("Oster")
    var usermima by mutableStateOf("Oster123")
    
    suspend fun login() {
        val result: Result<LoginResponseBodyResult> = landingRemoteDataSource.login(username, usermima)
        MainApplication.instance.cubcAppData.token = result.getOrNull()?.accessToken
    }
}