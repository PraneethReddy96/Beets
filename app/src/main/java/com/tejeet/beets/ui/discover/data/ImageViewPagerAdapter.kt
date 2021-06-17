package com.tejeet.beets.ui.discover.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tejeet.beets.R

class ImageViewPagerAdapter(val images : List<Int>) :RecyclerView.Adapter<ImageViewPagerAdapter.ViewPagerViewHolder>(){



    inner class ViewPagerViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.image_viewpager_item_layout,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {

        val item = images[position]

        holder.itemView.findViewById<ImageView>(R.id.ivSlider).setImageResource(item)


    }

    override fun getItemCount(): Int {

        return images.size
    }

}








