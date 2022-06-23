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
    private lateinit var textServerPort: TextInputLayout
    private lateinit var textSite: TextInputLayout
    private lateinit var textLocation: TextInputLayout
    private lateinit var textGateID: TextInputLayout
    private lateinit var textFTPUsername: TextInputLayout
    private lateinit var textFTPPassword: TextInputLayout
    private lateinit var textFTPPort: TextInputLayout
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
        textServerPort = findViewById(R.id.text_server_port)
        textSite = findViewById(R.id.text_site)
        textLocation = findViewById(R.id.text_location)
        textGateID = findViewById(R.id.text_gate_id)
        textFTPUsername = findViewById(R.id.text_ftp_username)
        textFTPPassword = findViewById(R.id.text_ftp_password)
        textFTPPort = findViewById(R.id.text_ftp_port)

        //Setting text
        textServerAddress.editText?.setText(Settings.SERVER_ADDRESS)
        textServerPort.editText?.setText(Settings.SERVER_PORT)
        textSite.editText?.setText(Settings.SITE)
        textLocation.editText?.setText(Settings.LOCATION)
        textGateID.editText?.setText(Settings.GATE_ID)
        textFTPUsername.editText?.setText(Settings.FTP_USERNAME)
        textFTPPassword.editText?.setText(Settings.FTP_PASSWORD)
        textFTPPort.editText?.setText(Settings.FTP_PORT.toString())
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
        val serverPort = textServerPort.editText?.text.toString()
        val location = textServerAddress.editText?.text.toString()
        val site = textSite.editText?.text.toString()
        val gateID = textGateID.editText?.text.toString()
        val ftpUsername = textFTPUsername.editText?.text.toString()
        val ftpPassword = textFTPPassword.editText?.text.toString()
        val ftpPort = textFTPPort.editText?.text.toString()
        var ftpPortInt = 0
        if (ftpPort != "null") ftpPortInt = ftpPort.toInt()

        if (listOf(
                serverAddress,
                serverPort,
                location,
                site,
                gateID,
                ftpUsername,
                ftpPassword,
                ftpPort
            ).any { it == "null" || it == "" }
        ) return false

        Settings.SERVER_ADDRESS = serverAddress
        Settings.SERVER_PORT = serverPort
        Settings.LOCATION = location
        Settings.SITE = site
        Settings.GATE_ID = gateID
        Settings.FTP_USERNAME = ftpUsername
        Settings.FTP_PASSWORD = ftpPassword
        Settings.FTP_PORT = ftpPortInt

        with(Settings.SHAREDPREFERENCE.edit()) {
            putString(getString(R.string.preference_server_address), serverAddress)
            putString(getString(R.string.preference_server_port), serverPort)
            putString(getString(R.string.preference_location), location)
            putString(getString(R.string.preference_site), site)
            putString(getString(R.string.preference_gate_id), gateID)
            putString(getString(R.string.preference_ftp_password),ftpPassword)
            putString(getString(R.string.preference_ftp_username),ftpUsername)
            putInt(getString(R.string.preference_ftp_port), ftpPortInt)
            apply()
        }

        return true
    }
}