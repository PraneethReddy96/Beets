package com.tejeet.beets.ui.discover.RecyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tejeet.beets.R
import com.tejeet.beets.ui.discover.data.modelClass.DataItem
import com.tejeet.beets.ui.discover.data.modelClass.Images
import com.tejeet.beets.utils.Constants

class natureAdapter(val dataItemList: MutableList<DataItem?>) : RecyclerView.Adapter<  natureTrendsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):   natureTrendsViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.trending6_item_layout,parent,false)
        return  natureTrendsViewHolder(view)
    }

    override fun onBindViewHolder(holder:   natureTrendsViewHolder, position: Int) {

        Glide.with(holder.IvImage).asGif()
            .load(dataItemList.get(position)?.images?.original?.url)
            .placeholder(Constants.getRandomDrawableColor())
            .into(holder.IvImage)

    }

    override fun getItemCount(): Int {

        return  dataItemList.size

    }
}


class natureTrendsViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){

    val IvImage = itemView.findViewById<ImageView>(R.id.recyclerImage6)


}