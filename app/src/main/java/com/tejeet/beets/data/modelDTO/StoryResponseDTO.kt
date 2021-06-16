package com.tejeet.beets.data.modelDTO


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class StoryResponseDTO(
    @SerializedName("noofstories")
    val noofstories: Int,
    @SerializedName("requeststatus")
    val requeststatus: Int,
    @SerializedName("stories_data")
    val storiesData: @RawValue List<StoriesData>
):Parcelable