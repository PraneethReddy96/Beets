package com.tejeet.beets.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.tejeet.beets.data.constant.AppPreferences
import com.tejeet.beets.ui.activities.interests.activity.InterestsActivity
import com.tejeet.beets.ui.activities.main.activity.MainActivity

class SplashScreen : AppCompatActivity() {
    private val TAG = "tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "Going to Main Activity")

        AppPreferences.init(this)

        getToken()
        initApp()
        onTokenRefresh()



        if (AppPreferences.is_FirstTime.equals("YES")){

            AppPreferences.is_FirstTime = "NO"
            startActivity(Intent(this, InterestsActivity::class.java))
            finish()
        }

        else{

            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }

    }

    fun onTokenRefresh(){

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            AppPreferences.userFirebaseToken = token.toString()

            Log.d(TAG, "Got new Token ${token}")
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })

    }

    fun initApp() {

        FirebaseMessaging.getInstance()
            .subscribeToTopic("general")
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