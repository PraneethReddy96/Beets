package com.tejeet.beets.ui.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.tejeet.beets.R
import com.tejeet.beets.data.model.StoriesData
import com.tejeet.beets.model.ResultData
import com.tejeet.beets.model.StoriesDataModel
import com.tejeet.beets.ui.home.adapter.StoriesPagerAdapter
import com.tejeet.beets.ui.main.viewmodel.MainViewModel
import com.tejeet.beets.utils.Constants
import com.tejeet.beets.work.PreCachingService
import com.tejeet.beets.databinding.FragmentHomeBinding
import com.tejeet.beets.utils.ViewUtils
import com.tejeet.beets.utils.ViewUtils.changeStatusBarColor
import com.tejeet.beets.utils.ViewUtils.hideStatusBar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private  val TAG = "tag"

    private var _binding: FragmentHomeBinding? = null
    private val mainViewModel: MainViewModel by viewModels()
    private val binding get() = _binding!!


    private lateinit var storiesPagerAdapter: StoriesPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        val storiesData = mainViewModel.getDataList()

        storiesData.observe(viewLifecycleOwner, Observer { value ->
            when(value) {
                is ResultData.Loading -> {
                    binding.lottieLoaderAnimation.visibility = View.VISIBLE
                    Log.d(TAG, "Loading")
                }
                is ResultData.Success -> {
                    if (!value.data.isNullOrEmpty()) {
                        binding.lottieLoaderAnimation.visibility = View.GONE
                        val dataList = value.data
                        storiesPagerAdapter = StoriesPagerAdapter(this, dataList)
                        binding.viewPagerStories.adapter = storiesPagerAdapter

                        startPreCaching(dataList)
                    }
                }
            }
        })

        return  binding.root
    }


    private fun startPreCaching(dataList: MutableList<StoriesData>) {
        val urlList = arrayOfNulls<String>(dataList.size)
        dataList.mapIndexed { index, storiesDataModel ->
            urlList[index] = storiesDataModel.storyUrl
        }
        val inputData = Data.Builder().putStringArray(Constants.KEY_STORIES_LIST_DATA, urlList).build()
        val preCachingWork = OneTimeWorkRequestBuilder<PreCachingService>().setInputData(inputData)
            .build()
        WorkManager.getInstance(requireContext())
            .enqueue(preCachingWork)
    }

    override fun onResume() {
        super.onResume()
        changeStatusBarColor(requireActivity(),R.color.transparent)
        hideStatusBar(requireActivity())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}