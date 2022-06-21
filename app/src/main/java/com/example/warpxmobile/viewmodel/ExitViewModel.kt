package com.example.warpxmobile.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.warpxmobile.R
import com.example.warpxmobile.model.request.CheckCardRequest
import com.example.warpxmobile.model.response.CheckCardResponse
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.device.reader.CardInfoEntity
import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
import com.nexgo.oaf.apiv3.device.reader.OnCardInfoListener
import kotlinx.coroutines.launch
import retrofit2.Response

class ExitViewModel : ViewModel() {
    val UID: MutableLiveData<Any> = MutableLiveData()
    var checkCardResponse: LiveData<Response<CheckCardResponse>> = MutableLiveData()

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
                Log.d("ExitViewModel", "Too many cards")
                UID.postValue(R.string.multiple_card)
            }
        })
    }

    fun postCheckCard() {
        val request = CheckCardRequest()
        viewModelScope.launch {
            //val response = RetrofitInstance.api.postEntrance("http://${Settings.SERVER_ADDRESS}/placeholder", request) //TODO: Replace placeholder
        }
        TODO("Implement HTTP request")
    }
}