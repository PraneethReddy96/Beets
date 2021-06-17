package com.tejeet.beets.data.network

import com.tejeet.beets.data.model.StoryResponseDTO
import com.tejeet.beets.data.model.upload.StoryUploadResponseDTO
import com.tejeet.beets.ui.discover.data.modelClass.SearchResponse
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

        @Part("storyupload") storyUpload : String,
        @Part("userID") userID:String,
        @Part("userEmail") userEmail:String,
        @Part("trustedAppKey") trustedAppKey:String,
        @Part("musicname") musicname:String,
        @Part("hashtag") hashtag:String,
        @Part("storyDescription") storyDescription:String,
        @Part file: MultipartBody.Part
    ) : StoryUploadResponseDTO








}