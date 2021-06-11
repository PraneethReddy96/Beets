package com.tejeet.beets.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tejeet.beets.model.ResultData
import com.tejeet.beets.model.StoriesDataModel
import com.tejeet.beets.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataRepository: DataRepository): ViewModel() {
    fun getDataList(): LiveData<ResultData<ArrayList<StoriesDataModel>?>> {
        return flow {
            emit(ResultData.Loading())
            emit(ResultData.Success(dataRepository.getStoriesData()))
        }.asLiveData(Dispatchers.IO)
    }
}