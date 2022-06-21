package com.example.warpxmobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.warpxmobile.R
import com.example.warpxmobile.viewmodel.EntranceViewModel
import com.nexgo.common.LogUtils
import com.nexgo.oaf.apiv3.APIProxy
import com.nexgo.oaf.apiv3.DeviceEngine

class EntranceActivity : AppCompatActivity() {
    private var deviceEngine: DeviceEngine? = null
    private val TAG = "EntranceActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

        deviceEngine = APIProxy.getDeviceEngine(this)

        val viewModel: EntranceViewModel = ViewModelProvider(this)[EntranceViewModel::class.java]

        LogUtils.setDebugEnable(true)
        findViewById<Button>(R.id.bt_readCard_entrance).setOnClickListener {
            viewModel.getUID(deviceEngine)
        }

        findViewById<Button>(R.id.bt_back_entranceGate).setOnClickListener {
            finish()
        }

        viewModel.UID.observe(this) { UID ->
            when (UID) {
                is Int -> {
                    Toast.makeText(this, getString(UID), Toast.LENGTH_SHORT).show()
                }
                is String -> {
                    findViewById<TextView>(R.id.text_uid_entrance).text = UID
                    //viewModel.postEntrance()
                }
            }
        }

        viewModel.httpResponse.observe(this) { response ->
            if (!response.isSuccessful) {
                Toast.makeText(this, "Request Failed: ${response.errorBody().toString()}", Toast.LENGTH_SHORT).show()
                Log.d(TAG, response.errorBody().toString())
                return@observe
            }
        }
    }
}