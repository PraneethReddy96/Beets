package com.tejeet.beets.ui.interests.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tejeet.beets.R
import com.tejeet.beets.ui.interests.data.Interests
import com.tejeet.beets.ui.interests.interfaces.ItemClickListener

class InterestAdapter(var interestslist: MutableList<Interests>?, var itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<InterestsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterestsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.interests_item_layout, parent, false)
        return InterestsViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: InterestsViewHolder, position: Int) {
        val interests: Interests = interestslist!![position]
        holder.setData(interests)
    }

    override fun getItemCount(): Int {
        return interestslist!!.size
    }

    init {
        this.interestslist = interestslist

        this.itemClickListener = itemClickListener
    }
}

class InterestsViewHolder(itemView: View, itemClickListener: ItemClickListener) :
    RecyclerView.ViewHolder(itemView) {
    private val cardInterest:CardView
    private val tvActivity: TextView
    private val ivTick :ImageView
    private val llContainer :LinearLayout
    private val itemClickListener: ItemClickListener

    fun setData(interests: Interests) {
        tvActivity.setText(interests.interests)
        if (interests.isSelected) {

           llContainer.setBackgroundColor(ContextCompat.getColor(llContainer.context,R.color.pink))
            tvActivity.setBackgroundColor(ContextCompat.getColor(tvActivity.context, R.color.pink))
            tvActivity.setTextColor(ContextCompat.getColor(tvActivity.context, R.color.colorWhite))
            ivTick.visibility =View.VISIBLE
        } else {
            llContainer.setBackgroundColor(ContextCompat.getColor(llContainer.context,R.color.colorWhite))
            ivTick.visibility=View.INVISIBLE
            tvActivity.setTextColor(ContextCompat.getColor(tvActivity.context, R.color.colorBlack))
            tvActivity.setBackgroundColor(ContextCompat.getColor(tvActivity.context, R.color.colorWhite))
        }
        tvActivity.setOnClickListener {
            itemClickListener.onInterestsClicked(absoluteAdapterPosition,interests)
        }
    }

    init {
        this.itemClickListener = itemClickListener
        tvActivity = itemView.findViewById(R.id.tvInterests)
        ivTick=itemView.findViewById(R.id.ivTick)
        llContainer=itemView.findViewById(R.id.llConatiner)
        cardInterest = itemView.findViewById(R.id.card_interest)

    }
}
