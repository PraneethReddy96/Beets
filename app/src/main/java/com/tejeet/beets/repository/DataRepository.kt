package com.tejeet.beets.repository

import com.tejeet.beets.data.model.StoriesData
import com.tejeet.beets.data.network.ApiService
import com.tejeet.beets.utils.Constants.API_KEY
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class DataRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getStoriesData():MutableList<StoriesData> {

        val dataList = apiService.getStory("OK",API_KEY)
        return dataList.body()!!.storiesData as MutableList<StoriesData>
    }
}