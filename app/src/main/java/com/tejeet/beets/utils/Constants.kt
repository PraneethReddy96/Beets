package com.tejeet.beets.utils

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowInsets
import androidx.annotation.ColorRes

object Constants {

    const val KEY_STORY_DATA = "KEY_STORY_DATA"
    const val KEY_STORIES_LIST_DATA = "KEY_STORY_DATA"
    const val BASE_URL = "https://beets.tejeet.com/"
    const val STORY_END_POINT = "api/app.php?"
    const val API_KEY = "U0xJzGoiDb13AsPmvZjrQFnT2qy9YaBl"
    const val TAG = "tag"

    fun showStatusAndNavBar(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowInsetsController = activity.window.insetsController
            windowInsetsController?.show(WindowInsets.Type.statusBars())
            windowInsetsController?.show(WindowInsets.Type.systemBars())
        }
    }

//    fun changeStatusBarColor(@ColorRes barColor: Int) {
//        window.apply {
//            statusBarColor = getColorFromRes(barColor)
//        }
//    }


}
