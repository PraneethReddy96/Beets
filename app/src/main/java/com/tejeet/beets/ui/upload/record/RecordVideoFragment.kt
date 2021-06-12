package com.tejeet.beets.ui.upload.record

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.controls.Audio
import com.otaliastudios.cameraview.controls.Facing
import com.otaliastudios.cameraview.controls.Flash
import com.otaliastudios.cameraview.controls.Mode
import com.otaliastudios.cameraview.gesture.Gesture
import com.otaliastudios.cameraview.gesture.GestureAction
import com.tejeet.beets.R
import com.tejeet.beets.databinding.FragmentRecordVideoBinding
import com.tejeet.beets.databinding.PermissionsLayoutBinding
import com.tejeet.beets.ui.upload.RecordViewModel
import com.tejeet.beets.utils.BottomNavViewUtils.hideBottomNavBar
import com.tejeet.beets.utils.PermissionUtils
import com.tejeet.beets.utils.PermissionUtils.arePermissionsGranted
import com.tejeet.beets.utils.PermissionUtils.recordVideoPermissions
import com.tejeet.beets.utils.SystemBarColors
import com.tejeet.beets.utils.ViewUtils.changeSystemBars
import com.tejeet.beets.utils.ViewUtils.hideStatusAndNavBar
import com.tejeet.beets.utils.ViewUtils.showStatusAndNavBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class RecordVideoFragment : Fragment() {

    private var _permission_binding: PermissionsLayoutBinding? = null
    private var _binding: FragmentRecordVideoBinding? = null
    private lateinit var recordViewModel: RecordViewModel
    private val binding get() = _binding!!
    private val permission_binding get() = _permission_binding!!
    private lateinit var cameraView: CameraView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recordViewModel = ViewModelProvider(requireActivity()).get(RecordViewModel::class.java)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRecordVideoBinding.inflate(inflater, container, false)
        _permission_binding = binding.permissionsLayout

        binding.lifecycleOwner = this
        binding.viewModel = recordViewModel

        cameraView = binding.cameraView

        initCameraView()
        changeUIBasedOnPermissions()
        turnOnCameraPreview()

        recordViewModel.localVideo.observe(viewLifecycleOwner, Observer { localVideo ->

            localVideo?.let {

//                findNavController().navigate(
//                    RecordVideoFragmentDirections
//                        .actionRecordVideoFragmentToPreviewVideoFragment(localVideo)
//                )

                recordViewModel.resetLocalVideo()
            }
        })

        setUpCameraSettings()
        setUpPermissionClickListeners()

        binding.closeBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.uploadImageBtn.setOnClickListener {
//            findNavController().navigate(R.id.action_recordVideoFragment_to_createVideoFragment)
        }

        binding.startRecordingBtn.setOnClickListener {

            if (recordViewModel.hasRecordingStarted.value == true)
                recordViewModel.resumeVideo()
            else{
                lifecycleScope.launch {
                    val fileDescriptor =
                        recordViewModel.startVideo(requireContext()) ?: return@launch
                    cameraView.takeVideo(fileDescriptor)
                }
            }
        }

        binding.pauseRecordingBtn.setOnClickListener {
            cameraView.stopVideo()
            recordViewModel.pauseVideo()
        }

        binding.finishRecordingBtn.setOnClickListener {
            if (recordViewModel.hasRecordingStarted.value == true){
                cameraView.stopVideo()
                recordViewModel.stopVideo(requireContext())
            }
        }


        return binding.root
    }




    private fun initCameraView() {
        cameraView.apply {
            facing = Facing.BACK
            audio = Audio.ON
            mode = Mode.VIDEO

            addCameraListener(recordViewModel.getCameraListener(requireContext()))

            mapGesture(Gesture.PINCH, GestureAction.ZOOM) // Pinch to zoom!
            mapGesture(Gesture.TAP, GestureAction.AUTO_FOCUS) // Tap to focus!
        }
    }

    private fun setUpPermissionClickListeners() {
        permission_binding.grantPermissionsBtn.setOnClickListener { requestPermissions() }
//        binding.permissionsLayout.grantPermissionsBtn.setOnClickListener { requestPermissions() }
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

        if (arePermissionsGranted(requireContext(), recordVideoPermissions)){
            turnOnCameraPreview()
            changeSystemBars(requireActivity(), SystemBarColors.DARK)

            hideBottomNavBar(requireActivity())
            hideStatusAndNavBar(requireActivity())
        }

     }

    override fun onResume() {
        super.onResume()
        changeUIBasedOnPermissions()
    }

    override fun onStop() {
        super.onStop()
        if (arePermissionsGranted(requireContext(), recordVideoPermissions)) {
            showStatusAndNavBar(requireActivity())
            cameraView.close()
            recordViewModel.stopVideo(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _permission_binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraView.destroy()
    }

}