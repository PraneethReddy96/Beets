package com.tejeet.beets.ui.discover.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tejeet.beets.R
import com.tejeet.beets.databinding.FragmentDiscoverBinding
import com.tejeet.beets.ui.discover.RecyclerViews.*
import com.tejeet.beets.ui.discover.data.ImageViewPagerAdapter
import com.tejeet.beets.ui.discover.data.modelClass.DataItem
import com.tejeet.beets.ui.discover.data.sliderModel
import com.tejeet.beets.ui.discover.viewmodel.DiscoverViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverFragment : Fragment() {


    private val discoverViewModel: DiscoverViewModel by viewModels()
    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!
    val modelList: MutableList<sliderModel> = mutableListOf()
    private var dataItemList = mutableListOf<DataItem?>()
    private var dataItemList1 = mutableListOf<DataItem?>()
    private var dataItemList2 = mutableListOf<DataItem?>()
    private var dataItemList3 = mutableListOf<DataItem?>()
    private var dataItemList4 = mutableListOf<DataItem?>()
    private var dataItemList5 = mutableListOf<DataItem?>()
    private var dataItemList6 = mutableListOf<DataItem?>()
    private var dataItemList7 = mutableListOf<DataItem?>()
    private var dataItemList8 = mutableListOf<DataItem?>()
    private var dataItemList9 = mutableListOf<DataItem?>()
    private lateinit var adapter1: euroAdapter
    private lateinit var adapter2: animeAdapter
    private lateinit var adapter3: k_popAdapter
    private lateinit var adapter4: NoRacismAdapter
    private lateinit var adapter5: danceTrendsAdapter
    private lateinit var adapter6: natureAdapter
    private lateinit var adapter7: travelAdapter
    private lateinit var adapter8: PopAdapter
    private lateinit var adapter9: marvelAdapter
    private lateinit var adapter10: wonderWomanAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)


        val images = listOf(


            R.drawable.imageslider2,
            R.drawable.imageslider,
            R.drawable.imageslider3,
            R.drawable.imageslider1,
            R.drawable.imageslider4,
            R.drawable.imageslider5,
            R.drawable.imageslider6,
            R.drawable.imageslider7,
            R.drawable.imageslider8,
            R.drawable.imageslider9

        )
        val adapter = ImageViewPagerAdapter(images)

        binding.slideViewPager.adapter = adapter


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViews()



    }

    private fun initRecyclerViews() {
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerView1.layoutManager = linearLayoutManager
        adapter1 = euroAdapter(dataItemList)
        binding.recyclerView1.adapter = adapter1



        discoverViewModel.callGifApi("Euro2020").observe(requireActivity(), Observer {

            dataItemList.clear()
            dataItemList.addAll(it)
            adapter1.notifyDataSetChanged()


        })


        val linearLayoutManager1 = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerView2.layoutManager = linearLayoutManager1
        adapter2 = animeAdapter(dataItemList1)
        binding.recyclerView2.adapter = adapter2



        discoverViewModel.callGifApi("anime").observe(requireActivity(), Observer {

            dataItemList1.clear()
            dataItemList1.addAll(it)
            adapter2.notifyDataSetChanged()


        })

        val linearLayoutManager2 = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerView3.layoutManager = linearLayoutManager2
        adapter3 = k_popAdapter(dataItemList2)
        binding.recyclerView3.adapter = adapter3



        discoverViewModel.callGifApi("k-pop").observe(requireActivity(), Observer {

            dataItemList2.clear()
            dataItemList2.addAll(it)
            adapter3.notifyDataSetChanged()


        })

        val linearLayoutManager3 = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerView4.layoutManager = linearLayoutManager3
        adapter4 = NoRacismAdapter(dataItemList3)
        binding.recyclerView4.adapter = adapter4



        discoverViewModel.callGifApi("no racism").observe(requireActivity(), Observer {

            dataItemList3.clear()
            dataItemList3.addAll(it)
            adapter4.notifyDataSetChanged()


        })

        val linearLayoutManager4 = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerView5.layoutManager = linearLayoutManager4
        adapter5 = danceTrendsAdapter(dataItemList4)
        binding.recyclerView5.adapter = adapter5



        discoverViewModel.callGifApi("dance trends").observe(requireActivity(), Observer {

            dataItemList4.clear()
            dataItemList4.addAll(it)
            adapter5.notifyDataSetChanged()


        })

        val linearLayoutManager5 = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerView6.layoutManager = linearLayoutManager5
        adapter6 =natureAdapter(dataItemList5)
        binding.recyclerView6.adapter = adapter6



        discoverViewModel.callGifApi("nature").observe(requireActivity(), Observer {

            dataItemList5.clear()
            dataItemList5.addAll(it)
            adapter6.notifyDataSetChanged()


        })

        val linearLayoutManager6 = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerView7.layoutManager = linearLayoutManager6
        adapter7 =travelAdapter(dataItemList6)
        binding.recyclerView7.adapter = adapter7



        discoverViewModel.callGifApi("travel").observe(requireActivity(), Observer {

            dataItemList6.clear()
            dataItemList6.addAll(it)
            adapter7.notifyDataSetChanged()


        })


        val linearLayoutManager7 = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerView8.layoutManager = linearLayoutManager7
        adapter8 =PopAdapter(dataItemList7)
        binding.recyclerView8.adapter = adapter8



        discoverViewModel.callGifApi("Pop").observe(requireActivity(), Observer {

            dataItemList7.clear()
            dataItemList7.addAll(it)
            adapter8.notifyDataSetChanged()


        })

        val linearLayoutManager8 = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerView9.layoutManager = linearLayoutManager8
        adapter9 =marvelAdapter(dataItemList8)
        binding.recyclerView9.adapter = adapter9



        discoverViewModel.callGifApi("marvel").observe(requireActivity(), Observer {

            dataItemList8.clear()
            dataItemList8.addAll(it)
            adapter9.notifyDataSetChanged()


        })

        val linearLayoutManager9 = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerView10.layoutManager = linearLayoutManager9
        adapter10 =wonderWomanAdapter(dataItemList9)
        binding.recyclerView10.adapter = adapter10



        discoverViewModel.callGifApi("wonder woman").observe(requireActivity(), Observer {

            dataItemList9.clear()
            dataItemList9.addAll(it)
            adapter10.notifyDataSetChanged()


        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}