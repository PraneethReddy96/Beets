package com.tejeet.beets.repository

import com.tejeet.beets.mock.Mock
import com.tejeet.beets.model.StoriesDataModel
import javax.inject.Inject

class DataRepository @Inject constructor(private val mock: Mock) {
    fun getStoriesData(): ArrayList<StoriesDataModel>? {
        val dataList = mock.loadMockData()
        return dataList
    }
}