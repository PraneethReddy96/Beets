package com.tejeet.beets.ui.discover.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.tejeet.beets.base.BaseFragment
import com.tejeet.beets.ui.discover.viewmodel.DiscoverViewModel
import com.tejeet.beets.R
import com.tejeet.beets.databinding.FragmentDiscoverBinding
import com.tejeet.beets.databinding.FragmentHomeBinding

class DiscoverFragment : BaseFragment(R.layout.fragment_discover) {

    private lateinit var viewModel: DiscoverViewModel
    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDiscoverBinding.inflate(inflater,container,false)

        viewModel = ViewModelProvider(this).get(DiscoverViewModel::class.java)

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}