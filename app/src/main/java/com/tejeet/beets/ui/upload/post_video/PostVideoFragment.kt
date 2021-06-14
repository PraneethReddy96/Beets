package com.tejeet.beets.ui.upload.post_video

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tejeet.beets.databinding.FragmentPostVideoBinding
import com.tejeet.beets.ui.main.viewmodel.MainViewModel
import com.tejeet.beets.ui.upload.preview_video.PreviewFragmentArgs
import com.tejeet.beets.ui.upload.record.RecordVideoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URI

@AndroidEntryPoint
class PostVideoFragment : Fragment() {

    private var _binding: FragmentPostVideoBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<PostVideoFragmentArgs>()

    private  val TAG = "PostVideoFragment"

    private val viewModel by viewModels<PostVideoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =  FragmentPostVideoBinding.inflate(inflater, container, false)

        val path = args.localVideo.filePath
//        val file = File(URI(path))

        binding.postBtn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch{
                val response = viewModel.uploadStory(File(path),"1","manish@gmail.com",
                    "Baby","#Trend","Description")

            }
        }





        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}