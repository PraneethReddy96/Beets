package com.tejeet.beets.data.modelDTO.upload


import com.google.gson.annotations.SerializedName

data class StoryUploadResponseDTO(
    @SerializedName("message")
    val message: String,
    @SerializedName("requeststatus")
    val requeststatus: Int,
    @SerializedName("tid")
    val tid: String
)