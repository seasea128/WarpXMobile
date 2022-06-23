package com.example.warpxmobile.model.request

data class GetTokenRequest(
    val USERID: String,
    val PASSWORD: String,
    val loginname: String,
    val loginpw: String
)
