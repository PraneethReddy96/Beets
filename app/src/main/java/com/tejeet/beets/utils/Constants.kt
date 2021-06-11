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

    fun hideStatusBar(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            activity.window.insetsController?.hide(WindowInsets.Type.statusBars())
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                activity.window.decorView.systemUiVisibility =
                    (
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE // Prevents layout resize
                                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // Initially load things behind the navigation bars. Prevents resize
                                    or View.SYSTEM_UI_FLAG_IMMERSIVE // Prevents the screen from showing the status bar, on tapping
                                    or View.SYSTEM_UI_FLAG_FULLSCREEN // Hide the status bar only
                            )
            }
        }
    }
}
