package com.example.warpxmobile.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import com.example.warpxmobile.R
import com.example.warpxmobile.utils.Settings
import com.example.warpxmobile.utils.Settings.Companion.GATE_ID
import com.example.warpxmobile.utils.Settings.Companion.PREFERENCE
import com.example.warpxmobile.utils.Settings.Companion.SERVER_ADDRESS
import com.example.warpxmobile.utils.Settings.Companion.SHAREDPREFERENCE
import com.example.warpxmobile.utils.Settings.Companion.SITE


class MainActivity : AppCompatActivity() {
    //TODO: Need to get strings from saved

    private val sharedPreferences by lazy {
        this.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Settings.SHAREDPREFERENCE = sharedPreferences
        loadSettings()
        setListener()
    }

    fun loadSettings() {
        Settings.SERVER_ADDRESS = sharedPreferences.getString(getString(R.string.preference_server_address), "").toString()
        Settings.SITE = sharedPreferences.getString(getString(R.string.preference_site), "").toString()
        Settings.LOCATION = sharedPreferences.getString(getString(R.string.preference_location), "").toString()
        Settings.GATE_ID = sharedPreferences.getString(getString(R.string.preference_gate_id), "").toString()
    }

    private fun setListener() {
        findViewById<Button>(R.id.bt_entranceGate).setOnClickListener {
            startActivity(Intent(this, EntranceActivity::class.java))
        }
        findViewById<Button>(R.id.bt_exitGate).setOnClickListener {
            startActivity(Intent(this, ExitActivity::class.java))
        }
        findViewById<Button>(R.id.bt_settings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        findViewById<Button>(R.id.bt_openGate).setOnClickListener {
            startActivity(Intent(this,OpenGateActivity::class.java))
        }
    }

}