package com.tejeet.beets.ui.discover.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.tejeet.beets.R
import com.tejeet.beets.databinding.FragmentDiscoverBinding
import com.tejeet.beets.ui.discover.data.ImageViewPagerAdapter
import com.tejeet.beets.ui.discover.data.sliderModel
import com.tejeet.beets.ui.discover.viewmodel.DiscoverViewModel

class DiscoverFragment : Fragment() {

    private lateinit var viewModel: DiscoverViewModel
    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!
    val modelList : MutableList<sliderModel> =  mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDiscoverBinding.inflate(inflater,container,false)


        val images = listOf(

            R.drawable.imageslider,
            R.drawable.imageslider1,
            R.drawable.imageslider2,
            R.drawable.imageslider3,
            R.drawable.imageslider4,
            R.drawable.imageslider5,
            R.drawable.imageslider6,
            R.drawable.imageslider7,
            R.drawable.imageslider8,
            R.drawable.imageslider9

        )

        val adapter = ImageViewPagerAdapter(images)

        binding.slideViewPager.adapter = adapter



        viewModel = ViewModelProvider(this).get(DiscoverViewModel::class.java)

        return binding.root
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}