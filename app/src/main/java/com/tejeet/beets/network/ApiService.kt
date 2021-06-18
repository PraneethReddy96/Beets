package com.tejeet.beets.data.network

import androidx.lifecycle.LiveData
import com.tejeet.beets.data.constants.GetAllUserResponseDTO
import com.tejeet.beets.data.modelDTO.FirebaseTokenUpdateResponseDTO
import com.tejeet.beets.data.modelDTO.LikeStoryResponseDTO
import com.tejeet.beets.data.modelDTO.StoryResponseDTO
import com.tejeet.beets.data.modelDTO.UserSignupDTO
import com.tejeet.beets.data.modelDTO.upload.StoryUploadResponseDTO
import com.tejeet.beets.utils.Constants.STORY_END_POINT
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*


interface ApiService {


    @GET(STORY_END_POINT)
    suspend fun getStory(
        @Query("getPosts") getPost : String,
        @Query("trustedAppKey") trustedAppKey:String
    ): Response<StoryResponseDTO>

    @Multipart
    @POST(STORY_END_POINT)
    suspend fun uploadStoryVideo(

        @Part("storyupload") storyUpload : String,
        @Part("userID") userID:String,
        @Part("userEmail") userEmail:String,
        @Part("trustedAppKey") trustedAppKey:String,
        @Part("musicname") musicname:String,
        @Part("hashtag") hashtag:String,
        @Part("storyDescription") storyDescription:String,
        @Part file: MultipartBody.Part
    ) : StoryUploadResponseDTO


    @GET(STORY_END_POINT)
    suspend fun updateFirebaseToken(
        @Query("userFBTokenUpdate") getPost : String,
        @Query("trustedAppKey") trustedAppKey:String,
        @Query("userEmail") userEmail : String,
        @Query("firebasetoken") firebaseToken : String
    ): Response<FirebaseTokenUpdateResponseDTO>

    @GET(STORY_END_POINT)
    suspend fun signupUser(
        @Query("userSignup") userSignup : String,
        @Query("trustedAppKey") trustedAppKey:String,
        @Query("userEmail") userEmail : String,
        @Query("displayName") displayName : String,
        @Query("userName") userName : String,
        @Query("userProfile") userProfileImage : String,
        @Query("firebasetoken") firebaseToken : String
    ): Response<UserSignupDTO>


    @GET(STORY_END_POINT)
    suspend fun likeStory(
        @Query("likeStory") likePost : String,
        @Query("trustedAppKey") trustedAppKey:String,
        @Query("storyID") storyID : String,
        @Query("likeCount") LikeCount : String,
        @Query("userID") userID : String
    ): Response<LikeStoryResponseDTO>

    @GET(STORY_END_POINT)
    suspend fun getAllUsers(
        @Query("getUsers") getUser : String,
        @Query("trustedAppKey") trustedAppKey:String,
        @Query("userID") userID : String
    ): Response<GetAllUserResponseDTO>



}