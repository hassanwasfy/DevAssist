package com.abaferas.devassist.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import javax.inject.Inject
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkStateManager @Inject constructor(
    private val context: Context
){
    fun isInternetAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            return if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)){
                true
            }else{
                throw DevAssistException.NoInternetConnection
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return if (activeNetworkInfo != null && activeNetworkInfo.isConnected){
                true
            }else{
                throw DevAssistException.NoInternetConnection
            }
        }
    }
}