package com.tejeet.beets.ui.upload.post_successful

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tejeet.beets.databinding.ActivityPostSuccessfulBinding
import com.tejeet.beets.ui.main.activity.MainActivity
import com.tejeet.beets.ui.upload.post_video.PostVideoFragmentArgs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PostSuccessfulActivity : AppCompatActivity() {

    lateinit var binding:ActivityPostSuccessfulBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding =  ActivityPostSuccessfulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            delay(4)

            var intent = Intent(this@PostSuccessfulActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}