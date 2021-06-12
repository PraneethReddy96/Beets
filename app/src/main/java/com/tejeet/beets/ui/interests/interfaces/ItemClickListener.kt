package com.tejeet.beets.ui.interests.interfaces

import com.tejeet.beets.ui.interests.data.Interests

interface ItemClickListener {

    fun onInterestsClicked(position: Int,interests: Interests?)

}