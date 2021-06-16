package com.tejeet.beets.data.network

import com.tejeet.beets.data.model.StoryResponseDTO
import com.tejeet.beets.utils.Constants.STORY_END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {


    @GET(STORY_END_POINT)
    suspend fun getStory(
        @Query("getPosts") getPost : String,
        @Query("trustedAppKey") trustedAppKey:String
    ): Response<StoryResponseDTO>







}