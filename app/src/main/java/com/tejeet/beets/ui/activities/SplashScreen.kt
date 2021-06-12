package com.tejeet.beets.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.tejeet.beets.ui.interests.activity.MainActivity

class SplashScreen : AppCompatActivity() {
    private val TAG = "tag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_splash_screen)

        Log.d(TAG, "Going to Main Activity")

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}