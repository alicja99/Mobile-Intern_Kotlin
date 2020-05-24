package com.alicja.sklepallegro.helper

import android.content.Context
import android.net.ConnectivityManager

object NetworkManager {
    fun isNetworkOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}