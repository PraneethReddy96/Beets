package com.tejeet.beets.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tejeet.beets.data.constants.AppPreferences
import com.tejeet.beets.ui.interests.activity.InterestsActivity

import com.tejeet.beets.ui.main.activity.MainActivity

class SplashScreen : AppCompatActivity() {
    private val TAG = "tag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_splash_screen)

        Log.d(TAG, "Going to Main Activity")


        AppPreferences.init(this)


        Log.d(TAG, "Checking IS First Time ${AppPreferences.is_FirstTime}")

        if (AppPreferences.is_FirstTime.equals("YES")){
            AppPreferences.is_FirstTime = "NO"
            startActivity(Intent(this, InterestsActivity::class.java))
            finish()
        }
        else{

            startActivity(Intent(this, MainActivity::class.java))
            finish();
        }




    }
}