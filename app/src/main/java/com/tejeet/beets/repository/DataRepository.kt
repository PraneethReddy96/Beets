package com.tejeet.beets.repository

import com.app.tiktok.mock.Mock
import com.tejeet.beets.model.StoriesDataModel
import javax.inject.Inject

class DataRepository @Inject constructor(private val mock: Mock) {
    fun getStoriesData(): ArrayList<StoriesDataModel>? {
        val dataList = mock.loadMockData()
        return dataList
    }
}