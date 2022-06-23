package com.example.warpxmobile.model.request

//TODO: Implement this
data class CheckCardRequest(
    val USERID: String,
    val PASSWORD: String,
    val VCNOS: String,
    val Tokencode: String,
    val SiteID: String,
    val LocationID: String,
    val Eq_id: String,
    val OPMODE: String,
    val adminname: String,
    val dayout: String,
    val Monthout: String,
    val Yearout: String,
    val Hourout: String,
    val Minout: String,
    val Secout: String,
    val LostCardFlag: String,
    val Shet: String,
)
