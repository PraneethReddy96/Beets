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

class PopAdapter(val dataItemList: MutableList<DataItem?>) : RecyclerView.Adapter<popViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):popViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.trending8_item_layout,parent,false)
        return  popViewHolder(view)
    }

    override fun onBindViewHolder(holder: popViewHolder, position: Int) {

        Glide.with(holder.IvImage).asGif().load(dataItemList.get(position)?.images?.original?.url).into(holder.IvImage)

    }

    override fun getItemCount(): Int {

        return  dataItemList.size

    }
}


class popViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){

    val IvImage = itemView.findViewById<ImageView>(R.id.recyclerImage8)


}