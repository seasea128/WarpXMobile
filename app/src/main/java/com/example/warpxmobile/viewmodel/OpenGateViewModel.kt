package com.example.warpxmobile.viewmodel

import android.util.Log
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.*
import com.example.warpxmobile.R
import com.example.warpxmobile.api.RetrofitInstance
import com.example.warpxmobile.model.request.CheckCardRequest
import com.example.warpxmobile.model.request.OpenGateRequest
import com.example.warpxmobile.model.response.CheckCardResponse
import com.example.warpxmobile.model.response.OpenGateResponse
import com.example.warpxmobile.utils.Settings
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.device.reader.CardInfoEntity
import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
import com.nexgo.oaf.apiv3.device.reader.OnCardInfoListener
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response

class OpenGateViewModel : ViewModel() {
    var openGateResponse: MutableLiveData<Response<OpenGateResponse>> = MutableLiveData()
    var errorToast: MutableLiveData<String> = MutableLiveData()

    fun postOpenGate() {
        val request = OpenGateRequest("","","","")
        viewModelScope.launch {
            try {
                openGateResponse.value = RetrofitInstance.api.postOpenGate(
                    "http://${Settings.SERVER_ADDRESS}/service/openGate",
                    request
                )
            } catch (e: Exception) {
                Log.d("OpenGateViewModel", e.toString())
                errorToast.value = e.toString()
            }
        }
    }
}