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

class danceTrendsAdapter(val dataItemList: MutableList<DataItem?>) : RecyclerView.Adapter< danceTrendsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  danceTrendsViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.trending5_item_layout,parent,false)
        return  danceTrendsViewHolder(view)
    }

    override fun onBindViewHolder(holder:  danceTrendsViewHolder, position: Int) {

        Glide.with(holder.IvImage).asGif().
        load(dataItemList.get(position)?.images?.original?.url)
            .placeholder(Constants.getRandomDrawableColor())
            .into(holder.IvImage)

    }

    override fun getItemCount(): Int {

        return  dataItemList.size

    }
}


class danceTrendsViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){

    val IvImage = itemView.findViewById<ImageView>(R.id.recyclerImage5)


}