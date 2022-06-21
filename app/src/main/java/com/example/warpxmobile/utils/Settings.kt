package com.example.warpxmobile.utils

import android.content.SharedPreferences

class Settings {
    companion object {
        const val PREFERENCE = "WarpXMobilePreference"
        lateinit var SHAREDPREFERENCE: SharedPreferences
        var SERVER_ADDRESS: String = ""
        var SITE: String = ""
        var LOCATION: String = ""
        var GATE_ID: String = ""
        var BASE_URL: String = "http://192.168.0.111/"
    }
}