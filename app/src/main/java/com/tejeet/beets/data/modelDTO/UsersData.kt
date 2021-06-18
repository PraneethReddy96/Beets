package com.tejeet.beets.data.constants


import com.google.gson.annotations.SerializedName

data class UsersData(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_img")
    val profileImg: String,
    @SerializedName("uid")
    val uid: String
)