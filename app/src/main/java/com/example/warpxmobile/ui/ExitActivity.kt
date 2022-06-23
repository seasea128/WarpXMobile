package com.example.warpxmobile.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.warpxmobile.R
import com.example.warpxmobile.viewmodel.ExitViewModel
import com.nexgo.common.LogUtils
import com.nexgo.oaf.apiv3.APIProxy
import com.nexgo.oaf.apiv3.DeviceEngine

class ExitActivity : AppCompatActivity() {
    private var deviceEngine: DeviceEngine? = null
    private val TAG = "ExitActivity"
    private lateinit var viewModel: ExitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exit)

        viewModel = ViewModelProvider(this)[ExitViewModel::class.java]

        deviceEngine = APIProxy.getDeviceEngine(this)
        LogUtils.setDebugEnable(true)

        setListener()
    }

    private fun setListener() {
        findViewById<Button>(R.id.bt_back_exitGate).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.bt_readCard_exit).setOnClickListener {
            viewModel.getUID(deviceEngine)
        }

        findViewById<Button>(R.id.bt_paybycash_exit).setOnClickListener {
            //TODO: Start new activity and call finish()
            finish()
        }

        findViewById<Button>(R.id.bt_paybyqr_exit).setOnClickListener {
            //TODO: Start new activity and call finish()
            finish()
        }

        viewModel.errorToast.observe(this) { str ->
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
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
                    viewModel.postCheckCard()
                }
            }
        }

        viewModel.checkCardResponse.observe(this) { response ->
            if (!response.isSuccessful) {
                Toast.makeText(
                    this,
                    "Request Failed: ${response.errorBody().toString()}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d(TAG, response.errorBody().toString())
                return@observe
            }
            viewModel.getImageLeft("")
            viewModel.getImageRight("")
            findViewById<Button>(R.id.bt_paybycash_exit).isEnabled = true
            findViewById<Button>(R.id.bt_paybyqr_exit).isEnabled = true
        }

        viewModel.imageLeft.observe(this) {
            findViewById<ImageView>(R.id.image_left_exit).setImageBitmap(it)
            findViewById<ImageView>(R.id.image_left_exit).visibility = ImageView.VISIBLE
        }

        viewModel.imageRight.observe(this) {
            findViewById<ImageView>(R.id.image_right_exit).setImageBitmap(it)
            findViewById<ImageView>(R.id.image_right_exit).visibility = ImageView.VISIBLE
        }

    }


}