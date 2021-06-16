package com.tejeet.beets.ui.upload.post_video

import androidx.lifecycle.ViewModel
import com.tejeet.beets.data.modelDTO.upload.StoryUploadResponseDTO
import com.tejeet.beets.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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
