package com.tejeet.beets.ui.fragments.inbox

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tejeet.beets.R
import com.tejeet.beets.data.constants.UsersData
import com.tejeet.beets.ui.discover.data.modelClass.DataItem

class InboxAdapter(var dataItemList: MutableList<UsersData>):RecyclerView.Adapter<InboxAdapter.InboxViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InboxViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.inbox_item_layout,parent,false)
        return  InboxViewHolder(view)
    }

    override fun onBindViewHolder(holder: InboxViewHolder, position: Int) {
        Glide.with(holder.tvUserImage).load(dataItemList[position].profileImg).into(holder.tvUserImage)

         holder.tvUserName.text = dataItemList[position].name
    }

    override fun getItemCount(): Int {
        return  dataItemList.size
    }

    fun updateList(newDataList:MutableList<UsersData>){
        dataItemList = newDataList
        notifyDataSetChanged()
    }


    class InboxViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvUserName = itemView.findViewById<TextView>(R.id.search_suggestion_name)
        val tvUserImage = itemView.findViewById<ImageView>(R.id.search_suggestion_icon)
    }
}