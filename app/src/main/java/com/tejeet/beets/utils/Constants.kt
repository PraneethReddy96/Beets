package com.tejeet.beets.utils

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.View
import android.view.WindowInsets
import androidx.annotation.ColorRes
import kotlin.random.Random

object Constants {

    const val KEY_STORY_DATA = "KEY_STORY_DATA"
    const val KEY_STORIES_LIST_DATA = "KEY_STORY_DATA"
    const val BASE_URL = "https://beets.tejeet.com/"
    const val STORY_END_POINT = "api/app.php?"
    const val API_KEY = "U0xJzGoiDb13AsPmvZjrQFnT2qy9YaBl"
    const val TAG = "tag"

    val vibrantLightColorList = arrayOf(
        ColorDrawable(Color.parseColor("#FF6347")), ColorDrawable(Color.parseColor("#40E0D0")),
        ColorDrawable(Color.parseColor("#D8BFD8")), ColorDrawable(Color.parseColor("#00FF7F")),
        ColorDrawable(Color.parseColor("#FFC0CB")), ColorDrawable(Color.parseColor("#DB7093")),
        ColorDrawable(Color.parseColor("#FFDAB9")), ColorDrawable(Color.parseColor("#BC8F8F")),
        ColorDrawable(Color.parseColor("#663399")), ColorDrawable(Color.parseColor("#800080")),
        ColorDrawable(Color.parseColor("#98FB98")), ColorDrawable(Color.parseColor("#F08080")),
        ColorDrawable(Color.parseColor("#E0FFFF")), ColorDrawable(Color.parseColor("#E6E6FA")),
        ColorDrawable(Color.parseColor("#D3D3D3")), ColorDrawable(Color.parseColor("#4B0082")),
        ColorDrawable(Color.parseColor("#00BFFF")), ColorDrawable(Color.parseColor("#00CED1")),
        ColorDrawable(Color.parseColor("#8FBC8F")), ColorDrawable(Color.parseColor("#E9967A")),
        ColorDrawable(Color.parseColor("#9ACCCD")), ColorDrawable(Color.parseColor("#8FD8A0")),
        ColorDrawable(Color.parseColor("#CBD890")), ColorDrawable(Color.parseColor("#DACC8F")),
        ColorDrawable(Color.parseColor("#7A5DC7")), ColorDrawable(Color.parseColor("#C8A2C8")),
        ColorDrawable(Color.parseColor("#FFCBA4")), ColorDrawable(Color.parseColor("#C12283")),
        ColorDrawable(Color.parseColor("#C3FDB8")), ColorDrawable(Color.parseColor("#FFDB58"))
    )

    fun getRandomDrawableColor(): ColorDrawable {
        var idx = Random.nextInt(vibrantLightColorList.size)
        return vibrantLightColorList[idx]
    }

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
