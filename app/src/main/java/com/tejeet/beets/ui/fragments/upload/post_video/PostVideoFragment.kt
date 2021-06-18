package com.tejeet.beets.ui.upload.post_video


import android.content.Context
import android.content.Intent
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
import com.tejeet.beets.data.constant.AppPreferences
import com.tejeet.beets.databinding.FragmentPostVideoBinding
import com.tejeet.beets.exoplayer.Player
import com.tejeet.beets.ui.fragments.upload.post_successful.PostSuccessfulActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
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

        AppPreferences.init(requireContext())

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


            val desc = binding.etDescription.text
            val music = binding.etMusicName.text
            val hashtag = binding.etHashtags.text

            if (desc.isEmpty()){
                binding.etDescription.error = "please fill description"
            }else if (music.isEmpty()){
                binding.etMusicName.error = "please enter music name"
            }else if(hashtag.isEmpty()){
                binding.etHashtags.error = "please enter hashtag"
            }else{
                binding.postLoadingAnim.visibility = View.VISIBLE
                binding.etDescription.visibility = View.GONE
                binding.etMusicName.visibility = View.GONE
                binding.etHashtags.visibility = View.GONE
                binding.playerView.visibility = View.GONE
                binding.postBtn.visibility = View.GONE
                CoroutineScope(Dispatchers.Main).launch{
                    viewModel.uploadStory(AppPreferences.userID.toString(),AppPreferences.userDisplayName.toString(),
                        music.toString(), hashtag.toString(),desc.toString(),File(path))
                    startActivity(Intent(activity, PostSuccessfulActivity::class.java))
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