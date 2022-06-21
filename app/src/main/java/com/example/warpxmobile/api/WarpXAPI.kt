package com.example.warpxmobile.api

import com.example.warpxmobile.model.request.*
import com.example.warpxmobile.model.response.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface WarpXAPI {
    @POST
    suspend fun postEntrance(
        @Url url: String,
        @Body entranceRequest: EntranceRequest
    ): Response<EntranceResponse>

    @POST
    suspend fun postCheckCard(
        @Url url: String,
        @Body checkCardRequest: CheckCardRequest
    ): Response<CheckCardResponse>

    @POST
    suspend fun postParkPay(
        @Url url: String,
        @Body payByCashRequest: ParkPayRequest
    ): Response<ParkPayResponse>

    @POST
    suspend fun postSaveLog(
        @Url url: String,
        @Body saveLogRequest: SaveLogRequest
    ): Response<SaveLogResponse>

    @POST
    suspend fun postOpenGate(@Url url: String, @Body openGateRequest: OpenGateRequest): Response<OpenGateResponse>
}