package com.tejeet.beets.ui.activities.main.viewmodel


import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*
import com.tejeet.beets.data.modelDTO.LikeStoryResponseDTO
import com.tejeet.beets.data.modelDTO.StoriesData
import com.tejeet.beets.data.modelDTO.UserSignupDTO
import com.tejeet.beets.model.ResultData
import com.tejeet.beets.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataRepository: DataRepository,
    application: Application
    ): AndroidViewModel(application) {

    fun getDataList(): LiveData<ResultData<MutableList<StoriesData>?>> {

            return flow {
                emit(ResultData.Loading())
                if (hasInternetConnection()){
                    emit(ResultData.Success(dataRepository.getStoriesData()))
                }else{
                    emit(ResultData.Exception("NO Internet Connection"))
                }


            }.asLiveData(Dispatchers.IO)

    }

     fun hasInternetConnection():Boolean{
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        )as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when{
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false;
        }
    }


    suspend fun likePost(storyId: String, likeCount : String, userID : String) : LikeStoryResponseDTO?{
        var likePostResponse = CoroutineScope(Dispatchers.IO).async {
            dataRepository.likeStoryPost(storyId,likeCount,userID)
        }
        return likePostResponse.await()
    }
}