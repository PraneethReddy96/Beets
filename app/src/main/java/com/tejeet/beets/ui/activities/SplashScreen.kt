package com.tejeet.beets.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import com.tejeet.beets.network.MyMessegingService
import com.tejeet.beets.ui.interests.activity.MainActivity

class SplashScreen : AppCompatActivity() {
    private val TAG = "tag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_splash_screen)

        Log.d(TAG, "Going to Main Activity")

        getToken()
        initApp()
        startActivity(Intent(this, MainActivity::class.java))

        finish()
    }

    fun onTokenRefresh(){

        // Get updated InstanceID token.
//        val refreshedToken = FirebaseInstanceId.getInstance().token
//        Log.d(
//            TAG, "Got Token Refreshed token: $refreshedToken"
//        )
    }

    fun initApp() {

        FirebaseMessaging.getInstance().subscribeToTopic("general")
            .addOnCompleteListener { task ->
                var msg = "Subscribed General topic"
                if (!task.isSuccessful) {
                    msg = "FB Toekn Failed"
                }
                Log.d(TAG, "Token Process $msg")
            }

    }

    fun getToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if(it.isComplete){
                val myToken = it.result.toString()
                Log.d(TAG, "Token Process $myToken")
            }
        }
    }




}