package com.tejeet.beets.ui.fragments.inbox

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.tejeet.beets.R
import com.tejeet.beets.data.constant.AppPreferences
import com.tejeet.beets.data.constants.UsersData
import com.tejeet.beets.data.modelDTO.StoriesData
import com.tejeet.beets.databinding.FragmentInboxBinding
import com.tejeet.beets.model.ResultData
import com.tejeet.beets.ui.activities.main.viewmodel.MainViewModel
import com.tejeet.beets.ui.home.adapter.StoriesPagerAdapter
import com.tejeet.beets.utils.ResUtils
import com.tejeet.beets.utils.ViewUtils
import com.tejeet.beets.utils.ViewUtils.showStatusBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InboxFragment : Fragment() {

    private var _binding: FragmentInboxBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()
    lateinit var inboxAdapter: InboxAdapter

    val dataList: MutableList<UsersData> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =  FragmentInboxBinding.inflate(inflater, container, false)

        AppPreferences.init(requireContext())


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerData()
        CoroutineScope(Dispatchers.Main).launch {
            mainViewModel.getAllUser(AppPreferences.userID.toString()).observe(viewLifecycleOwner,
                Observer {value->


                    when(value) {
                        is ResultData.Loading -> {
                            binding.lottieLoaderAnimation.visibility = View.VISIBLE
                            binding.lottieNoInternetConnection.visibility = View.GONE

                        }
                        is ResultData.Success -> {
                            if (value.data?.noofusers!! > 0) {
                                binding.lottieLoaderAnimation.visibility = View.GONE
                                binding.lottieNoInternetConnection.visibility = View.GONE
                                val dataList = value.data.usersData as MutableList
                                inboxAdapter.updateList(dataList)
                            }
                        }
                        is ResultData.Exception -> {
                            ResUtils.showSnackBar(requireView(), value.nothing.toString())
                            binding.lottieLoaderAnimation.visibility = View.GONE
                            binding.lottieNoInternetConnection.visibility = View.VISIBLE
                        }
                    }
                })
        }

    }

    private fun setUpRecyclerData() {
        binding.inboxRecyclerview.apply {
            inboxAdapter = InboxAdapter(dataList)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = inboxAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        ViewUtils.changeStatusBarColor(requireActivity(), R.color.colorBlack)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}