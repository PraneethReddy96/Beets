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

class travelAdapter(val dataItemList: MutableList<DataItem?>) : RecyclerView.Adapter< travelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):   travelViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.trending7_item_layout,parent,false)
        return   travelViewHolder(view)
    }

    override fun onBindViewHolder(holder:   travelViewHolder, position: Int) {

        Glide.with(holder.IvImage).asGif().load(dataItemList.get(position)?.images?.original?.url).into(holder.IvImage)

    }

    override fun getItemCount(): Int {

        return  dataItemList.size

    }
}


class  travelViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){

    val IvImage = itemView.findViewById<ImageView>(R.id.recyclerImage7)


}