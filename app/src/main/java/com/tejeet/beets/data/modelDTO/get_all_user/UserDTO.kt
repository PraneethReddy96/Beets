package com.tejeet.beets.data.modelDTO.get_all_user


import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("noofstories")
    val noofstories: Int,
    @SerializedName("requeststatus")
    val requeststatus: Int,
    @SerializedName("stories_data")
    val storiesData: List<StoriesData>
)