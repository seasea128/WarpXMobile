package com.example.warpxmobile.model.request

data class OpenGateRequest(
    val USERID: String,
    val PASSWORD: String,
    val Controller_ID: String,
    val Mode: String
)
