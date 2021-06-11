package com.tejeet.beets.ui.main.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.tejeet.beets.ui.main.viewmodel.MainViewModel
import com.tejeet.beets.R
import com.tejeet.beets.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity: AppCompatActivity(), NavController.OnDestinationChangedListener {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navController = findNavController(R.id.nav_host_fragment)
        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.navigation_home -> {

                window.apply {
                    statusBarColor = getColor(R.color.colorBlack)
                }

                val colorDark = ContextCompat.getColorStateList(
                    this,
                    R.color.bottom_tab_selector_item_dark
                )

                val colorBlack = ContextCompat.getColorStateList(
                    this,
                    R.color.colorBlack
                )

                binding.navView.backgroundTintList = colorBlack
                binding.navView.itemTextColor = colorDark
                binding.navView.itemIconTintList = colorDark
                binding.imageViewAddIcon.setImageResource(R.drawable.ic_add_icon_light)
            }
            else -> {

                window.apply {
                    statusBarColor = getColor(R.color.colorWhite)
                }
                val colorDark = ContextCompat.getColorStateList(
                    this,
                    R.color.bottom_tab_selector_item_light
                )

                val colorWhite = ContextCompat.getColorStateList(
                    this,
                    R.color.colorWhite
                )

                binding.navView.backgroundTintList = colorWhite
                binding.navView.itemTextColor = colorDark
                binding.navView.itemIconTintList = colorDark
                binding.imageViewAddIcon.setImageResource(R.drawable.ic_add_icon_dark)
            }
        }
    }
}