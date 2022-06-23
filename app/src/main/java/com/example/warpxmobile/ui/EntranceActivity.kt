package com.example.warpxmobile.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
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
    private lateinit var  viewModel: EntranceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

        deviceEngine = APIProxy.getDeviceEngine(this)

        viewModel = ViewModelProvider(this)[EntranceViewModel::class.java]

        LogUtils.setDebugEnable(true)

        setListener()
    }

    private fun setListener() {
        findViewById<Button>(R.id.bt_readCard_entrance).setOnClickListener {
            viewModel.getUID(deviceEngine)
        }

        findViewById<Button>(R.id.bt_back_entranceGate).setOnClickListener {
            finish()
        }

        viewModel.errorToast.observe(this) { str ->
            Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
        }

        viewModel.UID.observe(this) { UID ->
            when (UID) {
                is Int -> {
                    Toast.makeText(this, getString(UID), Toast.LENGTH_SHORT).show()
                }
                is String -> {
                    findViewById<TextView>(R.id.text_uid_entrance).text = UID
                    viewModel.postEntrance() //TODO: Put in values
                }
            }
        }

        viewModel.httpResponse.observe(this) { response ->
            if (!response.isSuccessful) {
                Toast.makeText(
                    this,
                    "Request Failed: ${response.errorBody().toString()}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d(TAG, response.errorBody().toString())
                return@observe
            }
            viewModel.getImageLeft("") //TODO: Fill in paths
            viewModel.getImageRight("")
            //TODO: Set text to the correct one
        }

        viewModel.imageLeft.observe(this) {
            findViewById<ImageView>(R.id.image_left_entrance).setImageBitmap(it)
            findViewById<ImageView>(R.id.image_left_entrance).visibility = ImageView.VISIBLE
        }

        viewModel.imageRight.observe(this) {
            findViewById<ImageView>(R.id.image_right_entrance).setImageBitmap(it)
            findViewById<ImageView>(R.id.image_right_entrance).visibility = ImageView.VISIBLE
        }
    }
}