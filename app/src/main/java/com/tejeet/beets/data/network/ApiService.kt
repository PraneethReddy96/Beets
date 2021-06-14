package com.tejeet.beets.data.network

import com.tejeet.beets.data.model.StoryResponseDTO
import com.tejeet.beets.data.model.upload.StoryUploadResponseDTO
import com.tejeet.beets.utils.Constants.STORY_END_POINT
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*


interface ApiService {


    @GET(STORY_END_POINT)
    suspend fun getStory(
        @Query("getPosts") getPost : String,
        @Query("trustedAppKey") trustedAppKey:String
    ): Response<StoryResponseDTO>

    @Multipart
    @POST(STORY_END_POINT)
    suspend fun uploadStoryVideo(

        @Query("storyupload") storyUpload : String,
        @Query("userID") userID:String,
        @Query("userEmail") userEmail:String,
        @Query("trustedAppKey") trustedAppKey:String,
        @Query("musicname") musicname:String,
        @Query("hashtag") hashtag:String,
        @Query("storyDescription") storyDescription:String,
        @Part file: MultipartBody.Part
    ) : StoryUploadResponseDTO


}