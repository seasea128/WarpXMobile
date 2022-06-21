package com.example.warpxmobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.warpxmobile.R
import com.example.warpxmobile.viewmodel.OpenGateViewModel

class OpenGateActivity : AppCompatActivity() {
    private lateinit var viewModel: OpenGateViewModel
    private val TAG = "OpenGateActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_gate)

        viewModel = ViewModelProvider(this)[OpenGateViewModel::class.java]
        setListener()
    }

    private fun setListener() {
       findViewById<Button>(R.id.bt_back_open_gate).setOnClickListener {
           finish()
       }
        findViewById<Button>(R.id.bt_open_open_gate).setOnClickListener {
            viewModel.postOpenGate()
        }

        viewModel.openGateResponse.observe(this) { response ->
            if (!response.isSuccessful) {
                Toast.makeText(this, "Request Failed: ${response.errorBody().toString()}", Toast.LENGTH_SHORT).show()
                Log.d(TAG, response.errorBody().toString())
                return@observe
            }
        }

        viewModel.errorToast.observe(this) { str ->
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
        }
    }
}