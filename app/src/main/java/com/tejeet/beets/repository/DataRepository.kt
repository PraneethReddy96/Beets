package com.tejeet.beets.repository

import com.tejeet.beets.data.model.StoriesData
import com.tejeet.beets.data.network.ApiService
import com.tejeet.beets.ui.discover.data.apiService
import com.tejeet.beets.ui.discover.data.modelClass.SearchResponse
import com.tejeet.beets.utils.Constants.API_KEY
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class DataRepository @Inject constructor(
     val apiService: ApiService
//     val ApiService : apiService
//     val searchKey: String? = "4wpLe98F1bo8C45KqdeO8BvP6RlWrtd2"
//     val limit: Int = 100
//     val language: String = "en"
//     val offset: Int = 0
//     val rating: String = "g"


) {
    suspend fun getStoriesData():MutableList<StoriesData> {

        val dataList = apiService.getStory("OK",API_KEY)
        return dataList.body()!!.storiesData as MutableList<StoriesData>
    }

//    suspend fun  getGifs(name: String):SearchResponse{
//
//        val list =  ApiService.Search(searchKey,name,limit,offset,rating,language)
//        return list.
//
//    }



}