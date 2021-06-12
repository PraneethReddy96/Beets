package com.tejeet.beets.ui.interests.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.tejeet.beets.R
import com.tejeet.beets.ui.main.activity.MainActivity

class SwipeActivity : AppCompatActivity() {

   lateinit var startWatchingBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe)

        initViews()
    }

    private fun initViews() {

        startWatchingBtn =findViewById(R.id.btnStartWatching)

        startWatchingBtn.setOnClickListener(View.OnClickListener {

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        })

    }


}