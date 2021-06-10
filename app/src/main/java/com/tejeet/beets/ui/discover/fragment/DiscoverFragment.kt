package com.tejeet.beets.ui.discover.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.tejeet.beets.base.BaseFragment
import com.tejeet.beets.ui.discover.viewmodel.DiscoverViewModel
import com.tejeet.beets.R

class DiscoverFragment : BaseFragment(R.layout.fragment_discover) {
    private lateinit var viewModel: DiscoverViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DiscoverViewModel::class.java)
    }
}