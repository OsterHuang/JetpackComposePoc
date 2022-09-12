package com.tp.cubc.poc

import android.app.Application
import android.util.Log
import com.tp.cubc.poc.app.CubcAppData
import dagger.hilt.android.HiltAndroidApp
import java.util.*
import javax.inject.Inject
import kotlin.properties.Delegates

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

@HiltAndroidApp
class MainApplication : Application() {
    companion object {
        var instance: MainApplication by Delegates.notNull()
    }
    @Inject
    lateinit var cubcAppData: CubcAppData

    override fun onCreate() {
        super.onCreate()
        instance = this

        initApplicationValues()
    }

    private fun initApplicationValues() {
        cubcAppData.deviceUUID = UUID.randomUUID().toString()
        Log.d(TAG, " [Key value] - deviceUUID: ${cubcAppData.deviceUUID}")
    }

}