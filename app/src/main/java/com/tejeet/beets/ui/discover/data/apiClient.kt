package com.tejeet.beets.ui.discover.data

import com.tejeet.beets.ui.discover.data.modelClass.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface apiClient {



    @GET("/v1/gifs/search")
    suspend fun Search(
        @Query("api_key") SearchKey: String,
        @Query("q") name: String,
        @Query("limit") num: Int,
        @Query("offset") `val`: Int,
        @Query("rating") pg: String,
        @Query("lang") language: String
    ): SearchResponse




}