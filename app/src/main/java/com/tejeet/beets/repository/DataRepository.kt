package com.tejeet.beets.repository

import com.tejeet.beets.data.modelDTO.FirebaseTokenUpdateResponseDTO
import com.tejeet.beets.data.modelDTO.StoriesData
import com.tejeet.beets.data.modelDTO.upload.StoryUploadResponseDTO
import com.tejeet.beets.network.ApiService
import com.tejeet.beets.utils.Constants.API_KEY
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject


@ActivityRetainedScoped
class DataRepository @Inject constructor(private val apiService: ApiService) {


    suspend fun getStoriesData(): MutableList<StoriesData> {

        val dataList = apiService.getStory("OK", API_KEY)
        return dataList.body()?.storiesData as MutableList<StoriesData>
    }


    suspend fun uploadStoryVideo(
        userId: String,
        userEmail: String, musicName: String,
        hashTag: String, storyDesc: String, rawFile: File
    ): StoryUploadResponseDTO {

        val filePart = MultipartBody.Part.createFormData(
            "storyVideo",
            rawFile.name, rawFile.asRequestBody()
        )


        val dataList: StoryUploadResponseDTO = apiService.uploadStoryVideo(
            "OK", userId,
            userEmail, API_KEY, musicName, hashTag, storyDesc, filePart
        )

        return dataList
    }

    suspend fun updateFirebaseToken(email : String , firebaseToekn : String) : FirebaseTokenUpdateResponseDTO?{

        val response = apiService.updateFirebaseToken("OK", API_KEY, email, firebaseToekn )
        return response.body()

    }


}