package com.tejeet.beets.ui.Settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tejeet.beets.R
import com.tejeet.beets.utils.ViewUtils

class settingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    override fun onResume() {
        super.onResume()
        ViewUtils.changeStatusBarColor(this, R.color.colorBlack)
    }
}