/*
 * Copyright (c) 2020 jesusd0897.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jesusd0897.kutil

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresPermission

enum class NetworkType {
    NONE, MOBILE, WIFI, VPN
}

/**
 * Detect if there is any type of network connection.
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun hasNetwork(context: Context): Boolean = networkType(context) != NetworkType.NONE

/**
 * Identify the current network type.
 * @return Network type detected.
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun networkType(context: Context): NetworkType {
    var result = NetworkType.NONE
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        if (capabilities != null)
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->
                    result = NetworkType.WIFI
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->
                    result = NetworkType.MOBILE
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) ->
                    result = NetworkType.VPN
            }
    } else {
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null)
            when (activeNetwork.type) {
                ConnectivityManager.TYPE_WIFI -> result = NetworkType.WIFI
                ConnectivityManager.TYPE_MOBILE -> result = NetworkType.MOBILE
                ConnectivityManager.TYPE_VPN -> result = NetworkType.VPN
            }

    }
    return result
}