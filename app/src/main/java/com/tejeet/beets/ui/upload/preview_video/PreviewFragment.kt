package com.tejeet.beets.ui.upload.preview_video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.tejeet.beets.data.model.upload.LocalVideo
import com.tejeet.beets.databinding.FragmentPreviewBinding
import com.tejeet.beets.exoplayer.Player


class PreviewFragment : Fragment() {

    private var _binding: FragmentPreviewBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<PreviewFragmentArgs>()
    private val localVideo by lazy { args.localVideo }

    private val player by lazy {
//         Player(
//            simpleExoplayerView = binding.playerView,
//            playBtn = binding.playBtn,
//            context = requireContext(),
//            url = localVideo.filePath,
//            onVideoEnded = {
//                it.restartPlayer()
//            }
//        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =  FragmentPreviewBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}