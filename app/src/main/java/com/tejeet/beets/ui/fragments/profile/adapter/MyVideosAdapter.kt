package com.tejeet.beets.ui.fragments.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tejeet.beets.R
import com.tejeet.beets.data.modelDTO.StoriesData
import com.tejeet.beets.ui.fragments.profile.MyVideosClickListener


class MyVideosAdapter(var dataList: MutableList<StoriesData>,var myVideosClickListener: MyVideosClickListener)
    : RecyclerView.Adapter<MyVideosAdapter.MyVideosViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVideosViewHolder {
        val  view =  LayoutInflater.from(parent.context).inflate(R.layout.my_videos_item_layout,parent,false)
        return  MyVideosViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyVideosViewHolder, position: Int) {

        val requestOptions = RequestOptions()
        Glide
            .with(holder.myVideosContainer)
            .applyDefaultRequestOptions(requestOptions)
            .load(dataList[position].storyUrl)
            .into(holder.myVideosContainer)
        Glide.with(holder.myVideosContainer).load(dataList[position].storyUrl).into(holder.myVideosContainer)

        holder.myVideosContainer.setOnClickListener {
            myVideosClickListener.onItemClicked(dataList[position])
        }
    }



    override fun getItemCount(): Int {
        return dataList.size
    }

     fun setData( newDataList:MutableList<StoriesData>){
        dataList = newDataList
         notifyDataSetChanged()

    }

    class MyVideosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val myVideosContainer = itemView.findViewById<ImageView>(R.id.myVideosImage)

    }



}


