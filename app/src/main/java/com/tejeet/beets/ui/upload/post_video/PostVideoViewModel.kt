package com.tejeet.beets.ui.upload.post_video

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.tejeet.beets.data.model.StoriesData
import com.tejeet.beets.data.model.upload.StoryUploadResponseDTO
import com.tejeet.beets.model.ResultData
import com.tejeet.beets.repository.DataRepository
import com.tejeet.beets.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PostVideoViewModel @Inject constructor(
    private val dataRepository: DataRepository
):ViewModel(){


suspend fun uploadStory( userId:String,
                        userEmail:String, musicName:String,
                        hashTag:String, storyDesc:String , rawFile: File): StoryUploadResponseDTO{

   val response =  CoroutineScope(Dispatchers.IO).async {

        dataRepository.uploadStoryVideo(userId,
            userEmail,musicName,hashTag,storyDesc,rawFile)

    }


    return response.await()
    }
}
