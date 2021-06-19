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

class wonderWomanAdapter(val dataItemList: MutableList<DataItem?>) : RecyclerView.Adapter<wonderWomanViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):wonderWomanViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.trending10_item_layout,parent,false)
        return  wonderWomanViewHolder(view)
    }

    override fun onBindViewHolder(holder: wonderWomanViewHolder, position: Int) {

        Glide.with(holder.IvImage).asGif()
            .load(dataItemList.get(position)?.images?.original?.url)
            .placeholder(Constants.getRandomDrawableColor())
            .into(holder.IvImage)

    }

    override fun getItemCount(): Int {

        return  dataItemList.size

    }
}


class wonderWomanViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){

    val IvImage = itemView.findViewById<ImageView>(R.id.recyclerImage10)


}