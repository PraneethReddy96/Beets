package com.tejeet.beets.ui.discover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.tejeet.beets.model.ResultData
import com.tejeet.beets.repository.DataRepository
import com.tejeet.beets.ui.discover.data.modelClass.DataItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(val repository :DataRepository) :  ViewModel() {



    fun callGifApi(name : String): LiveData<MutableList<DataItem?>>{

        return flow {

            emit(repository.getGifs(name))

        }.asLiveData(Dispatchers.IO)


    }


}