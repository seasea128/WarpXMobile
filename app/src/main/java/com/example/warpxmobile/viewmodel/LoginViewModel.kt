package com.example.warpxmobile.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warpxmobile.api.RetrofitInstance
import com.example.warpxmobile.model.request.GetTokenRequest
import com.example.warpxmobile.model.response.GetTokenResponse
import com.example.warpxmobile.utils.Settings
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel : ViewModel() {
    val loginResponse: MutableLiveData<Response<GetTokenResponse>> = MutableLiveData()
    val errorToast: MutableLiveData<String> = MutableLiveData()
    val TAG = "LoginViewModel"

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val request = GetTokenRequest(username,password,"","")
            try {
                loginResponse.value = RetrofitInstance.api.getToken("${Settings.getURI()}/placeholder", request) // TODO: replace placeholder
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
                e.printStackTrace()
                errorToast.value = e.toString()
            }
        }
    }
}