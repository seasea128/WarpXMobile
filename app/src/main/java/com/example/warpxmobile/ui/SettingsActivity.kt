package com.example.warpxmobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.warpxmobile.R
import com.example.warpxmobile.utils.Settings
import com.example.warpxmobile.utils.Settings.Companion.GATE_ID
import com.example.warpxmobile.utils.Settings.Companion.SERVER_ADDRESS
import com.example.warpxmobile.utils.Settings.Companion.SITE
import com.google.android.material.textfield.TextInputLayout

class SettingsActivity : AppCompatActivity() {

    private lateinit var back: Button
    private lateinit var save: Button
    private lateinit var textServerAddress: TextInputLayout
    private lateinit var textSite: TextInputLayout
    private lateinit var textLocation: TextInputLayout
    private lateinit var textGateID: TextInputLayout
    private val TAG = "SettingsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        init()
        setListener()
    }

    private fun init() {
        back = findViewById(R.id.bt_back_settings)
        save = findViewById(R.id.bt_save_settings)
        textServerAddress = findViewById(R.id.text_server_address)
        textSite = findViewById(R.id.text_site)
        textLocation = findViewById(R.id.text_location)
        textGateID = findViewById(R.id.text_gate_id)

        //Setting text
        textServerAddress.editText?.setText(Settings.SERVER_ADDRESS)
        textSite.editText?.setText(Settings.SITE)
        textLocation.editText?.setText(Settings.LOCATION)
        textGateID.editText?.setText(Settings.GATE_ID)
    }

    private fun setListener() {
        back.setOnClickListener {
            finish()
        }

        save.setOnClickListener {
            val success = saveSettings()
            Log.d(TAG, "Success: $success")
            if (success) {
                finish()
            } else {
                Toast.makeText(this, R.string.blank_setting, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveSettings(): Boolean {
        val serverAddress = textServerAddress.editText?.text.toString()
        val location = textServerAddress.editText?.text.toString()
        val site = textSite.editText?.text.toString()
        val gateID = textGateID.editText?.text.toString()

        if (listOf(
                serverAddress,
                location,
                site,
                gateID
            ).any { it == "null" || it == "" }
        ) return false

        Settings.SERVER_ADDRESS = serverAddress
        Settings.LOCATION = location
        Settings.SITE = site
        Settings.GATE_ID = gateID

        with(Settings.SHAREDPREFERENCE.edit()) {
            putString(getString(R.string.preference_server_address), serverAddress)
            putString(getString(R.string.preference_location), location)
            putString(getString(R.string.preference_site), site)
            putString(getString(R.string.preference_gate_id), gateID)
            apply()
        }

        return true
    }
}