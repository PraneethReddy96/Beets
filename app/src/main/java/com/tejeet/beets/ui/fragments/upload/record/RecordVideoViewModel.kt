

package com.tejeet.beets.ui.upload.record

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.VideoResult
import com.tejeet.beets.data.modelDTO.upload.LocalRecordLocation
import com.tejeet.beets.data.modelDTO.upload.LocalVideo
import com.tejeet.beets.repository.record.DefaultRecordVideoRepo
import com.tejeet.beets.utils.architecture.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.FileDescriptor
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class RecordVideoViewModel  @Inject constructor (

    private val recordVideoRepo: DefaultRecordVideoRepo
) : BaseViewModel() {

    private var timeCreated by Delegates.notNull<Long>()
    private var localRecordLocation: LocalRecordLocation? = null

    private val _localVideo = MutableLiveData<LocalVideo?>()
    val localVideo: LiveData<LocalVideo?> = _localVideo

    private val _showLittleSpace = MutableLiveData(false)
    val showLittleSpace: LiveData<Boolean> = _showLittleSpace

    private val _hasRecordingStarted = MutableLiveData(false)
    val hasRecordingStarted: LiveData<Boolean> = _hasRecordingStarted

    private val _isRecording = MutableLiveData(false)
    val isRecording: LiveData<Boolean> = _isRecording

    /**
     * This function sets hasRecordingStarted to true and retrieves a new uri and its corresponding file descriptor
     */
    suspend fun startVideo(context: Context): FileDescriptor? {
//        if (!userRepo.doesDeviceHaveAnAccount()) {
//            showMessage(R.string.account_needed_to_record_video)
//            return null
//        }
//        if (!localSpaceRepo.hasEnoughSpace()) {
//            _showLittleSpace.value = true
//            return null
//        }
        _hasRecordingStarted.value = true
        _isRecording.value = true
        timeCreated = System.currentTimeMillis()

        localRecordLocation = recordVideoRepo.initVideo(context, timeCreated)
        return localRecordLocation?.fileDescriptor
    }

    fun resumeVideo() {
        _isRecording.value = true
    }

    fun pauseVideo() {
        _isRecording.value = false
    }

    fun stopVideo(context: Context) {
        if (_hasRecordingStarted.value == true) {
            _isRecording.value = false
            _hasRecordingStarted.value = false

            viewModelScope.launch {
                recordVideoRepo.stopVideo(context)
            }
        }
    }

    fun getCameraListener(context: Context) = object : CameraListener() {
        override fun onVideoRecordingStart() {
            super.onVideoRecordingStart()
            Timber.d("Video recording has started")
        }

        override fun onVideoRecordingEnd() {
            super.onVideoRecordingEnd()
            Timber.d("Video recording has ended")
        }

        override fun onVideoTaken(result: VideoResult) {
            super.onVideoTaken(result)
            Timber.d("Video has been taken. Result.contentUri ${localRecordLocation?.contentUri} and result.filePath is ${localRecordLocation?.filePath}")


            viewModelScope.launch {
                val duration = getVideoDuration(context)
                Timber.d("duration is $duration")

                _localVideo.value = LocalVideo(
                    localRecordLocation?.filePath,
                    duration?.toLong() ?: 0,
                    timeCreated.toString()
                )
            }
        }
    }

    suspend fun getVideoDuration(context: Context) = withContext(Dispatchers.IO) {
        val mediaPlayer = MediaPlayer.create(context, localRecordLocation?.filePath?.toUri())
        val duration = mediaPlayer?.duration
        mediaPlayer?.release()

        return@withContext duration
    }

    fun resetLocalVideo() {
        _localVideo.value = null
    }

    fun resetShowLittleLayout() {
        _showLittleSpace.value = false
    }
}