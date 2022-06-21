package com.example.warpxmobile.model.request

data class EntranceRequest(
    val USERID: String,
    val PASSWORD: String,
    val VCNOS: String,
    val Tokencode: String,
    val SiteID: String,
    val LocationID: String,
    val Eq_id: String,
    val OP_MODE: Int,
    val adminname: String,
    val Dayout: String,
    val Monthout: String,
    val Yearout: String,
    val Hourout: String,
    val Minout: String,
    val Secout: String,
    val LostCardFlag: String,
    val Shet: Int
)
