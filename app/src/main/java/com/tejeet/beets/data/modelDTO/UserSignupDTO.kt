package com.tejeet.beets.data.modelDTO

import com.google.gson.annotations.SerializedName

data class UserSignupDTO(
    @SerializedName("message")
    val message: String,
    @SerializedName("requeststatus")
    val requeststatus: Int,
    @SerializedName("tid")
    val tid: String,
    @SerializedName("userID")
    val userID: String,
    @SerializedName("user_profile")
    val userProfile: Any,
    @SerializedName("useremail")
    val useremail: String,
    @SerializedName("username")
    val username: String

)