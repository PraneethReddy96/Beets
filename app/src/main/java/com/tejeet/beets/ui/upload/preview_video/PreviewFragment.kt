package com.tejeet.beets.ui.upload.preview_video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.SimpleExoPlayer
import com.tejeet.beets.R
import com.tejeet.beets.data.model.upload.LocalVideo
import com.tejeet.beets.databinding.FragmentPreviewBinding
import com.tejeet.beets.exoplayer.Player
import com.tejeet.beets.utils.BottomNavViewUtils.hideBottomNavBar
import com.tejeet.beets.utils.SystemBarColors
import com.tejeet.beets.utils.ViewUtils


class PreviewFragment : Fragment() {

    private var _binding: FragmentPreviewBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<PreviewFragmentArgs>()
    lateinit var player: Player



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =  FragmentPreviewBinding.inflate(inflater, container, false)

        player =
            Player(
                simpleExoplayerView = binding.playerView,
                playBtn = binding.playBtn,
                context = requireContext(),
                url = args.localVideo.filePath,
                onVideoEnded = {
                    it.restartPlayer()
                }
            )

        binding.nextBtn.setOnClickListener {
//            findNavController().navigate(
//                PreviewVideoFragmentDirections
//                    .actionPreviewVideoFragmentToPostVideoFragment(localVideo)
//            )
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        player.resumePlayer()
        ViewUtils.changeSystemBars(activity, SystemBarColors.DARK)
        ViewUtils.changeSystemNavigationBarColor(requireActivity(), R.color.colorBlack)
        ViewUtils.hideStatusBar(requireActivity())
        hideBottomNavBar(activity)
    }

    override fun onPause() {
        super.onPause()
        player.pausePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stopPlayer()
    }

}