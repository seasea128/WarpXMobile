package com.example.warpxmobile.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warpxmobile.R
import com.example.warpxmobile.api.FTPHelper
import com.example.warpxmobile.api.RetrofitInstance
import com.example.warpxmobile.model.request.EntranceRequest
import com.example.warpxmobile.model.response.EntranceResponse
import com.example.warpxmobile.utils.Settings
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.device.reader.CardInfoEntity
import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
import com.nexgo.oaf.apiv3.device.reader.OnCardInfoListener
import kotlinx.coroutines.launch
import retrofit2.Response
import java.net.ConnectException

class EntranceViewModel : ViewModel() {
    private val TAG = "EntranceViewModel"
    val UID: MutableLiveData<Any> = MutableLiveData()
    val httpResponse: MutableLiveData<Response<EntranceResponse>> = MutableLiveData()
    val errorToast: MutableLiveData<String> = MutableLiveData()
    val imageLeft: MutableLiveData<Bitmap> = MutableLiveData()
    val imageRight: MutableLiveData<Bitmap> = MutableLiveData()

    fun getUID(deviceEngine: DeviceEngine?) {
        val slot: HashSet<CardSlotTypeEnum> = HashSet()
        slot.add(CardSlotTypeEnum.RF)

        deviceEngine?.cardReader?.searchCard(slot, 60, object : OnCardInfoListener {
            override fun onCardInfo(retCode: Int, cardInfo: CardInfoEntity) {
                if (cardInfo.cardExistslot != CardSlotTypeEnum.RF) {
                    return
                }
                UID.postValue(
                    deviceEngine.m1CardHandler.readUid().chunked(2).reversed().joinToString("")
                )
            }

            override fun onSwipeIncorrect() {
                UID.postValue(R.string.incorrect_swipe)
            }

            override fun onMultipleCards() {
                UID.postValue(R.string.multiple_card)
            }
        })
    }

    fun getImageLeft(path: String) {
        viewModelScope.launch {
            try {
                imageLeft.value = FTPHelper.getImage(path)
            } catch (e: ConnectException) {
                Log.d(TAG, e.toString())
                e.printStackTrace()
                errorToast.value = "Cannot connect to server (FTP Passive Mode)"
                return@launch
            } catch (e: NullPointerException) {
                Log.d(TAG, e.toString())
                e.printStackTrace()
                errorToast.value = "File not exist (FTP Passive Mode)"
                return@launch
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                e.printStackTrace()
                errorToast.value = e.toString()
            }
        }
    }

    fun getImageRight(path: String) {
        viewModelScope.launch {
            try {
                imageRight.value = FTPHelper.getImage(path)
            } catch (e: ConnectException) {
                Log.d(TAG, e.toString())
                e.printStackTrace()
                errorToast.value = "Cannot connect to server (FTP Passive Mode)"
                return@launch
            } catch (e: NullPointerException) {
                Log.d(TAG, e.toString())
                e.printStackTrace()
                errorToast.value = "File not exist (FTP Passive Mode)"
                return@launch
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                e.printStackTrace()
                errorToast.value = e.toString()
            }
        }
    }


    fun postEntrance() {
        val request = EntranceRequest(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            0,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            0
        )
        viewModelScope.launch {
            try {
                httpResponse.value = RetrofitInstance.api.postEntrance(
                    "${Settings.getURI()}/placeholder",
                    request
                ) //TODO: Replace placeholder
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                e.printStackTrace()
                errorToast.value = e.toString()
            }
        }
    }
}