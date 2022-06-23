package com.example.warpxmobile.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.warpxmobile.R
import com.example.warpxmobile.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputLayout

class LoginActivity2 : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var textUsername: TextInputLayout
    private lateinit var textPassword: TextInputLayout
    private lateinit var btLogin: Button
    private var isSetting = false
    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        isSetting = intent.getBooleanExtra("Settings", false)
        init()
        setListener()
    }

    private fun setListener() {
        btLogin.setOnClickListener {
            success()
            //val username = textUsername.editText?.text.toString()
            //val password = textPassword.editText?.text.toString()
            //if (listOf(username, password).any { it == "null" || it == "" }) {
            //    Toast.makeText(this, "Username and Password cannot be empty", Toast.LENGTH_SHORT)
            //        .show()
            //    return@setOnClickListener
            //}
            //viewModel.login(username, password)
        }

        viewModel.errorToast.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loginResponse.observe(this) { response ->
            if (!response.isSuccessful) {
                Toast.makeText(
                    this,
                    "Request Failed: ${response.errorBody().toString()}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d(TAG, response.errorBody().toString())
                return@observe
            }

            success()
        }
    }

    private fun success() {
        if (isSetting) {
            startActivity(Intent(this, SettingsActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, SiteActivity::class.java))
        }
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        textUsername = findViewById(R.id.text_username)
        textPassword = findViewById(R.id.text_password)
        btLogin = findViewById(R.id.bt_login)
    }
}