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

class k_popAdapter(val dataItemList: MutableList<DataItem?>) : RecyclerView.Adapter<K_popViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): K_popViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.trending3_item_layout,parent,false)
        return  K_popViewHolder(view)
    }

    override fun onBindViewHolder(holder: K_popViewHolder, position: Int) {

        Glide.with(holder.IvImage).asGif().load(dataItemList.get(position)?.images?.original?.url).into(holder.IvImage)

    }

    override fun getItemCount(): Int {

        return  dataItemList.size

    }
}


class K_popViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView){

    val IvImage = itemView.findViewById<ImageView>(R.id.recyclerImage3)


}