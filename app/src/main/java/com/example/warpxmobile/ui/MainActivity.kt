package com.example.warpxmobile.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.warpxmobile.R
import com.example.warpxmobile.utils.Settings
import com.example.warpxmobile.utils.Settings.Companion.PREFERENCE


class MainActivity : AppCompatActivity() {

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

    private fun loadSettings() {
        Settings.SERVER_ADDRESS =
            sharedPreferences.getString(getString(R.string.preference_server_address), "")
                .toString()
        Settings.SERVER_PORT =
            sharedPreferences.getString(getString(R.string.preference_server_port), "").toString()
        Settings.SITE =
            sharedPreferences.getString(getString(R.string.preference_site), "").toString()
        Settings.LOCATION =
            sharedPreferences.getString(getString(R.string.preference_location), "").toString()
        Settings.GATE_ID =
            sharedPreferences.getString(getString(R.string.preference_gate_id), "").toString()
        Settings.FTP_PASSWORD =
            sharedPreferences.getString(getString(R.string.preference_ftp_password), "").toString()
        Settings.FTP_USERNAME =
            sharedPreferences.getString(getString(R.string.preference_ftp_username), "").toString()
        Settings.FTP_PORT = sharedPreferences.getInt(getString(R.string.preference_ftp_port), 21)
    }

    private fun setListener() {
        findViewById<Button>(R.id.bt_entranceGate).setOnClickListener {
            startActivity(Intent(this, EntranceActivity::class.java))
        }
        findViewById<Button>(R.id.bt_exitGate).setOnClickListener {
            startActivity(Intent(this, ExitActivity::class.java))
        }
        findViewById<Button>(R.id.bt_settings).setOnClickListener {
            val intent = Intent(this,LoginActivity2::class.java)
                .putExtra("Settings", true)
            startActivity(intent)
        }
        findViewById<Button>(R.id.bt_openGate).setOnClickListener {
            startActivity(Intent(this, OpenGateActivity::class.java))
        }
        findViewById<Button>(R.id.bt_lostCard).setOnClickListener {
            startActivity(Intent(this, LostCardActivity::class.java))
        }
        findViewById<Button>(R.id.bt_logout).setOnClickListener {
            finish()
        }
    }

}