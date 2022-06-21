package com.example.warpxmobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.warpxmobile.R
import com.example.warpxmobile.viewmodel.ExitViewModel
import com.nexgo.common.LogUtils
import com.nexgo.oaf.apiv3.APIProxy
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.device.reader.CardInfoEntity
import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
import com.nexgo.oaf.apiv3.device.reader.OnCardInfoListener

class ExitActivity : AppCompatActivity() {
    private var deviceEngine: DeviceEngine? = null
    private val TAG = "ExitActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exit)

        val viewModel = ViewModelProvider(this)[ExitViewModel::class.java]

        deviceEngine = APIProxy.getDeviceEngine(this)
        LogUtils.setDebugEnable(true)

        findViewById<Button>(R.id.bt_back_exitGate).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.bt_readCard_exit).setOnClickListener {
            viewModel.getUID(deviceEngine)
        }

        viewModel.UID.observe(this) { UID ->
            when (UID) {
                is Int -> {
                    Log.d(TAG, getString(UID))
                    Toast.makeText(this, getString(UID), Toast.LENGTH_SHORT).show()
                    viewModel.UID.postValue("")
                }
                is String -> {
                    findViewById<TextView>(R.id.text_uid_exit).text = UID
                }
            }
        }
    }
}