package com.tejeet.beets.data.modelDTO

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class FirebaseTokenUpdateResponseDTO(
    @SerializedName("message")
    val message: String,
    @SerializedName("requeststatus")
    val requeststatus: Int,
    @SerializedName("token")
    val token: String
)