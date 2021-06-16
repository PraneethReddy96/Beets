package com.tejeet.beets.data.modelDTO


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoriesData(
    @SerializedName("commentsCount")
    val commentsCount: String,
    @SerializedName("contentWarning")
    val contentWarning: String,
    @SerializedName("likesCount")
    val likesCount: String,
    @SerializedName("musicCoverImageLink")
    val musicCoverImageLink: String,
    @SerializedName("musicCoverTitle")
    val musicCoverTitle: String,
    @SerializedName("storyDescription")
    val storyDescription: String,
    @SerializedName("storyId")
    val storyId: String,
    @SerializedName("storyThumbUrl")
    val storyThumbUrl: String,
    @SerializedName("storyUrl")
    val storyUrl: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("userProfilePicUrl")
    val userProfilePicUrl: String
):Parcelable