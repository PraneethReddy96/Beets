
package com.tejeet.beets.ui.upload.record

import android.content.Context
import android.content.Intent
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.controls.Audio
import com.otaliastudios.cameraview.controls.Facing
import com.otaliastudios.cameraview.controls.Flash
import com.otaliastudios.cameraview.controls.Mode
import com.otaliastudios.cameraview.gesture.Gesture
import com.otaliastudios.cameraview.gesture.GestureAction
import com.tejeet.beets.R
import com.tejeet.beets.data.modelDTO.upload.LocalVideo
import com.tejeet.beets.databinding.FragmentRecordVideoBinding
import com.tejeet.beets.utils.BottomNavViewUtils.hideBottomNavBar
import com.tejeet.beets.utils.BottomNavViewUtils.showBottomNavBar
import com.tejeet.beets.utils.PermissionUtils
import com.tejeet.beets.utils.PermissionUtils.arePermissionsGranted
import com.tejeet.beets.utils.PermissionUtils.recordVideoPermissions
import com.tejeet.beets.utils.SystemBarColors
import com.tejeet.beets.utils.ViewUtils.changeSystemBars
import com.tejeet.beets.utils.ViewUtils.hideStatusBar
import com.tejeet.beets.utils.ViewUtils.showStatusBar
import com.tejeet.beets.utils.architecture.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.File
import java.util.*

@AndroidEntryPoint
class RecordVideoFragment : BaseFragment(R.layout.fragment_record_video) {

    private lateinit var binding: FragmentRecordVideoBinding
    private lateinit var cameraView: CameraView

    final val VIDEO_RECORD_CODE = 101

    override val viewModel by viewModels<RecordVideoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cameraView = binding.cameraView
        initCameraView()
        changeUIBasedOnPermissions()
    }

    private fun initCameraView() {

        cameraView.apply {
            facing = Facing.BACK
            audio = Audio.ON
            mode = Mode.VIDEO

            addCameraListener(viewModel.getCameraListener(requireContext()))

            mapGesture(Gesture.PINCH, GestureAction.ZOOM) // Pinch to zoom!
            mapGesture(Gesture.TAP, GestureAction.AUTO_FOCUS) // Tap to focus!
        }
    }

    override fun setUpLayout() {
        binding = FragmentRecordVideoBinding.bind(requireView()).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
        }
    }

    override fun setUpLiveData() {
        super.setUpLiveData()
        viewModel.showLittleSpace.observe(viewLifecycleOwner) { hasLittleSpace ->
            binding.littleSpaceLayout.root.visibility =
                if (hasLittleSpace) View.VISIBLE else View.GONE
        }

        viewModel.localVideo.observe(viewLifecycleOwner) { localVideo ->
            localVideo?.let {
                findNavController().navigate(
                    RecordVideoFragmentDirections.actionNavigationRecordToNavigationPreview(localVideo)
                )
                viewModel.resetLocalVideo()
            }
        }

    }

    override fun setUpClickListeners() {
        setUpCameraSettings()
        setUpPermissionClickListeners()
        setLittleSpaceClickListeners()



        binding.closeBtn.setOnClickListener { findNavController().popBackStack() }
//        binding.uploadImageBtn.setOnClickListener {
//            findNavController().navigate(R.id.action_recordVideoFragment_to_createVideoFragment)
//        }

        binding.startRecordingBtn.setOnClickListener {

            recordVideo()
//
//            // Resume recording
//            if (viewModel.hasRecordingStarted.value == true)
//                viewModel.resumeVideo()
//            // Start recording
//            else {
//                lifecycleScope.launch {
//                    val fileDescriptor =
//                        viewModel.startVideo(requireContext()) ?: return@launch
//                    cameraView.takeVideo(fileDescriptor)
//                }
//            }
        }

        binding.pauseRecordingBtn.setOnClickListener {
            cameraView.stopVideo()
            viewModel.pauseVideo()
        }

//        binding.finishRecordingBtn.setOnClickListener {
//            if (viewModel.hasRecordingStarted.value == true) {
//                cameraView.stopVideo()
//                viewModel.stopVideo(requireContext())
//            }
//        }
    }

    private fun setLittleSpaceClickListeners() {
        binding.littleSpaceLayout.littleSpaceBtn.setOnClickListener {
            viewModel.resetShowLittleLayout()
        }
    }

    private fun setUpPermissionClickListeners() {
        binding.permissionsLayout.grantPermissionsBtn.setOnClickListener { requestPermissions() }
    }

    private fun turnOnCameraPreview() {
        Timber.d("turnOnCameraPreview called")
        cameraView.open()
    }


    private fun setUpCameraSettings() {
        binding.flipCameraBtn.setOnClickListener {
            cameraView.facing = if (cameraView.facing == Facing.FRONT) Facing.BACK else Facing.FRONT
        }
        binding.flashBtn.setOnClickListener {
            val isFlashOff = cameraView.flash == Flash.OFF

            cameraView.flash = if (isFlashOff) Flash.ON else Flash.OFF
            binding.flashBtn.setImageResource(
                if (isFlashOff) R.drawable.ic_round_flash_on else R.drawable.ic_round_flash_off
            )
        }
    }

    private val dialogMultiplePermissionsListener by lazy {
        PermissionUtils.DialogMultiplePermissionsListener(
            view = requireView(),
            onPermissionsGranted = {
                binding.permissionsLayout.root.visibility = View.GONE
                turnOnCameraPreview()
            },
            onPermissionsDenied = { binding.permissionsLayout.root.visibility = View.VISIBLE }
        )
    }

    private fun requestPermissions() {
        PermissionUtils.requestPermissions(
            requireContext(),
            dialogMultiplePermissionsListener,
            recordVideoPermissions
        )
    }

    private fun changeUIBasedOnPermissions() {
        if (arePermissionsGranted(requireContext(), recordVideoPermissions)) {
           // turnOnCameraPreview()
            changeSystemBars(requireActivity(), SystemBarColors.DARK)
            hideBottomNavBar(requireActivity())
            hideStatusBar(requireActivity())
          //  hideStatusAndNavBar(requireActivity())
            binding.permissionsLayout.root.visibility = View.GONE
            turnOnCameraPreview()
        }
        else {
            showBottomNavBar(requireActivity())
            showStatusBar(requireActivity())
            //showStatusAndNavBar(requireActivity())
            binding.permissionsLayout.root.visibility = View.VISIBLE
        }
    }



    private fun recordVideo(){
        var intent  = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        startActivityForResult(intent,VIDEO_RECORD_CODE)


        val localVideo = LocalVideo("dkdkdk",9999,"399393")



    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == VIDEO_RECORD_CODE) {
            val videoUri: Uri? = data?.data
            videoUri?.let {
                val duration = getMediaDurationMs(requireContext(),it)
                val timeCreated = getTimeCreated(it)
                val localVideo = LocalVideo(it.toString(), duration?.toLong(),timeCreated)
                findNavController().navigate(
                    RecordVideoFragmentDirections.actionNavigationRecordToNavigationPreview(localVideo)
                )
            }
        }
    }
    fun getTimeCreated(fileUri:Uri):String{
        val file = File(fileUri.path)
        val lastModDate = Date(file.lastModified())
        return lastModDate.toString()
    }
    fun getMediaDurationMs(context: Context, fileUri: Uri): Int? {
        val mmr = MediaMetadataRetriever()
        mmr.setDataSource(context, fileUri)
        return mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toInt()
    }

    override fun onResume() {
        super.onResume()
        changeUIBasedOnPermissions()
    }

    override fun onStop() {
        super.onStop()
        if (arePermissionsGranted(requireContext(), recordVideoPermissions)) {
           // showStatusAndNavBar(requireActivity())
            cameraView.close()
            viewModel.stopVideo(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraView.destroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        showBottomNavBar(requireActivity())
    }
}