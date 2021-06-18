package com.tejeet.beets.ui.fragments.profile

import com.tejeet.beets.data.modelDTO.StoriesData

interface MyVideosClickListener {

    fun onItemClicked(storiesData: StoriesData)
}