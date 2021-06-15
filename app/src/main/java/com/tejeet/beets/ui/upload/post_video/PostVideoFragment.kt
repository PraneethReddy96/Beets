package com.tejeet.beets.ui.upload.post_video


import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tejeet.beets.databinding.FragmentPostVideoBinding
import com.tejeet.beets.exoplayer.Player
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File


@AndroidEntryPoint
class PostVideoFragment : Fragment() {

    private var _binding: FragmentPostVideoBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<PostVideoFragmentArgs>()
    lateinit var player: Player

    private  val TAG = "PostVideoFragment"

    private val viewModel by viewModels<PostVideoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding =  FragmentPostVideoBinding.inflate(inflater, container, false)


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
        lifecycle.addObserver(player)
        player.init()


//      val file = File(URI(path))


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val file = args.localVideo.filePath

        val path = getRealPathFromURI(requireContext(),file?.toUri())

        binding.postBtn.setOnClickListener {


            val desc = binding.etDescription.toString()
            val music = binding.etMusicName.toString()
            val hashtag = binding.etHashtags.toString()

            if (desc.isEmpty()){
                binding.etDescription.error = "please fill description"
            }else if (music.isEmpty()){
                binding.etMusicName.error = "please enter music name"
            }else if(hashtag.isEmpty()){
                binding.etHashtags.error = "please enter hashtag"
            }else{
                CoroutineScope(Dispatchers.Main).launch{
                    val response = viewModel.uploadStory("1","manish@gmail.com",
                        music, hashtag,desc,File(path))
                    val dd = response
                }
            }
        }
    }

    fun getRealPathFromURI(context: Context, contentUri: Uri?): String? {
        var cursor: Cursor? = null
        return try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.getContentResolver().query(contentUri!!, proj, null, null, null)
            val column_index: Int = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(column_index)
        } finally {
            if (cursor != null) {
                cursor.close()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}