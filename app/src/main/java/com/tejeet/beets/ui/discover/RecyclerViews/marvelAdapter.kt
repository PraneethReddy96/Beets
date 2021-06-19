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

class marvelAdapter(val dataItemList: MutableList<DataItem?>) : RecyclerView.Adapter<marvelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):marvelViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.trending9_item_layout,parent,false)
        return  marvelViewHolder(view)
    }

    override fun onBindViewHolder(holder: marvelViewHolder, position: Int) {

        Glide.with(holder.IvImage).asGif()
            .load(dataItemList.get(position)?.images?.original?.url)
            .placeholder(Constants.getRandomDrawableColor())
            .into(holder.IvImage)

    }

    override fun getItemCount(): Int {

        return  dataItemList.size

    }
}


class marvelViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){

    val IvImage = itemView.findViewById<ImageView>(R.id.recyclerImage9)


}