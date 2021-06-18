package com.tejeet.beets.data.modelDTO

import com.google.gson.annotations.SerializedName

data class LikeStoryResponseDTO(
    @SerializedName("message")
    val message: String,
    @SerializedName("requeststatus")
    val requeststatus: Int,
    @SerializedName("tid")
    val tid: String
)