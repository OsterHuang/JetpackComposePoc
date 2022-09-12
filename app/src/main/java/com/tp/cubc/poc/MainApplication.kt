package com.tp.cubc.poc

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

@HiltAndroidApp
class MainApplication : Application() {
}