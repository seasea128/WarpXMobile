package com.example.warpxmobile.model.response

data class EntranceResponse(
    val returncode: Int,
    val returnmessage: String,
    val result: Int,
    val VCID: String,
    val TIMEIN: String,
    val VCNOS: String,
    val Status: String,
    val chkDBflag: String,
    val PicIN1: String,
    val PicIN2: String,
    val PicIN3: String,
    val PicIN4: String,
    val PicOut1: String,
    val PicOut2: String,
    val PicOut3: String,
    val PicOut4: String,
)