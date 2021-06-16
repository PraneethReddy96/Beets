package com.tejeet.beets.ui.activities.interests.interfaces

import com.tejeet.beets.ui.activities.interests.data.Interests

interface ItemClickListener {

    fun onInterestsClicked(position: Int,interests: Interests?)

}