package com.example.warpxmobile.utils

import android.content.SharedPreferences

class Settings {
    companion object {
        const val PREFERENCE = "WarpXMobilePreference"
        lateinit var SHAREDPREFERENCE: SharedPreferences
        var SERVER_ADDRESS: String = ""
        var SERVER_PORT: String = ""
        var SITE: String = ""
        var LOCATION: String = ""
        var GATE_ID: String = ""
        var BASE_URL: String = "http://192.168.0.111/"
        var FTP_USERNAME: String = ""
        var FTP_PASSWORD: String = ""
        var FTP_PORT: Int = 21

        var USERNAME: String = ""
        var PASSWORD: String = ""
        fun getURI(): String {
            return "http://$SERVER_ADDRESS:$SERVER_PORT"
        }
    }
}