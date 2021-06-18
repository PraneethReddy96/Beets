package com.tejeet.beets.data.constants


import com.google.gson.annotations.SerializedName

data class GetAllUserResponseDTO(
    @SerializedName("noofusers")
    val noofusers: Int,
    @SerializedName("requeststatus")
    val requeststatus: Int,
    @SerializedName("users_data")
    val usersData: List<UsersData>
)