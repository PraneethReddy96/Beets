package com.tejeet.beets.ui.fragments.profile.viewmodel

import androidx.lifecycle.ViewModel
import com.tejeet.beets.data.modelDTO.FirebaseTokenUpdateResponseDTO
import com.tejeet.beets.data.modelDTO.UserSignupDTO
import com.tejeet.beets.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private  val dataRepository: DataRepository
) : ViewModel() {

    suspend fun updateFirebaseToken(email : String, fbToken : String ) : FirebaseTokenUpdateResponseDTO?{
        var updateResponse = CoroutineScope(Dispatchers.IO).async {
             dataRepository.updateFirebaseToken(email, fbToken)
        }
        return updateResponse.await()

    }

    suspend fun signupUserAccount(displayName: String, userName : String, email : String, userProfile : String, firebaseToken : String ) : UserSignupDTO?{
        var signupResponse = CoroutineScope(Dispatchers.IO).async {
            dataRepository.updateSignUPAccount(displayName, userName, email, userProfile, firebaseToken)
        }

        return signupResponse.await()
    }


}