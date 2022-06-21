package com.example.warpxmobile.viewmodel

import androidx.lifecycle.*
import com.example.warpxmobile.R
import com.example.warpxmobile.api.RetrofitInstance
import com.example.warpxmobile.model.request.EntranceRequest
import com.example.warpxmobile.model.response.EntranceResponse
import com.example.warpxmobile.utils.Settings
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.device.reader.CardInfoEntity
import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
import com.nexgo.oaf.apiv3.device.reader.OnCardInfoListener
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Response

class EntranceViewModel : ViewModel() {
    val UID: MutableLiveData<Any> = MutableLiveData()
    var httpResponse: MutableLiveData<Response<EntranceResponse>> = MutableLiveData()

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

    fun postEntrance() {
        TODO("Implement HTTP request")
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
            //val response = RetrofitInstance.api.postEntrance("http://${Settings.SERVER_ADDRESS}/placeholder", request) //TODO: Replace placeholder
        }
    }
}