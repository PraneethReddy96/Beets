package com.tejeet.beets.ui.fragments.profile.autoplay_video

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.Player
import com.tejeet.beets.R
import com.tejeet.beets.data.modelDTO.StoriesData
import com.tejeet.beets.databinding.TiktokTimelineItemRecyclerBinding


class TikTokRecyclerAdapter(
    private val mContext: Context,
    private var modelList: MutableList<StoriesData>
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), PlayerStateCallback {

    private var mItemClickListener: OnItemClickListener? = null

    fun updateList(modelList: MutableList<StoriesData>) {
        this.modelList = modelList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): VideoPlayerViewHolder {
        val binding: TiktokTimelineItemRecyclerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context)
            , R.layout.tiktok_timeline_item_recycler, viewGroup, false)
        return VideoPlayerViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {

        //Here you can fill your row view
        if (holder is VideoPlayerViewHolder) {
            val model = getItem(position)
            val genericViewHolder = holder

            // send data to view holder
            genericViewHolder.onBind(model)
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    private fun getItem(position: Int): StoriesData {
        return modelList[position]
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        val position = holder.adapterPosition
        PlayerViewAdapter.releaseRecycledPlayers(position)
        super.onViewRecycled(holder)
    }

    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(
            view: View?,
            position: Int,
            model: StoriesData?
        )
    }

    inner class VideoPlayerViewHolder(private val binding: TiktokTimelineItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: StoriesData) {
            // handel on item click
            binding.root.setOnClickListener {
                mItemClickListener!!.onItemClick(
                    it,
                    adapterPosition,
                    model
                )
            }

            binding.apply {
                dataModel = model
                callback = this@TikTokRecyclerAdapter
                index = adapterPosition
                executePendingBindings()
            }
        }
    }

    override fun onVideoDurationRetrieved(duration: Long, player: Player) {
    }

    override fun onVideoBuffering(player: Player) {
    }

    override fun onStartedPlaying(player: Player) {

    }

    override fun onFinishedPlaying(player: Player) {
    }

}