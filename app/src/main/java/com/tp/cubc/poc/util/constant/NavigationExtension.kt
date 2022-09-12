package com.tp.cubc.poc.util.constant

import android.os.Parcelable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

fun NavController.navigate(route: String, vararg arguments: Pair<String, Parcelable>) {
    navigate(route)

    requireNotNull(currentBackStackEntry?.arguments).apply {
        arguments.forEach { (key: String, arg: Parcelable) ->
            putParcelable(key, arg)
        }
    }
}

inline fun <reified T : Parcelable> NavBackStackEntry.arguments(key: String): T {
    return requireNotNull(arguments) { "arguments bundle is null" }.run {
        requireNotNull(getParcelable(key)) { "argument for $key is null" }
    }
}