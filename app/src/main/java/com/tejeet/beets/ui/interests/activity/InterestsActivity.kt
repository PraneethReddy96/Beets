package com.tejeet.beets.ui.interests.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tejeet.beets.R
import com.tejeet.beets.ui.interests.adapter.InterestAdapter
import com.tejeet.beets.ui.interests.data.Interests
import com.tejeet.beets.ui.interests.interfaces.ItemClickListener
import com.tejeet.beets.ui.main.activity.MainActivity
import java.util.*

class InterestsActivity : AppCompatActivity(), ItemClickListener {
    private var recyclerView: RecyclerView? =null
    private var interestsList: MutableList<Interests>? = null
   lateinit var adapter: InterestAdapter
  lateinit var skip : Button
  lateinit var next : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest)
        initViewsAndListeners()
    }

    private fun initViewsAndListeners() {
        recyclerView = findViewById(R.id.RecyclerView)
        buildRecyclerData()
        setRecyclerAdapter()


        skip = findViewById(R.id.btnSkip)
        next = findViewById(R.id.btnNext)

       skip.setOnClickListener(View.OnClickListener {
           val intent = Intent(this, MainActivity::class.java)
           startActivity(intent)
       })

        next.setOnClickListener(View.OnClickListener {
           val intent = Intent(this, SwipeActivity::class.java)
            startActivity(intent)

       })



    }

    private fun setRecyclerAdapter() {
        adapter = InterestAdapter(interestsList, this)
        val gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView!!.adapter = adapter
        recyclerView!!.layoutManager = gridLayoutManager
    }

    private fun buildRecyclerData() {
        interestsList = ArrayList<Interests>()
        interestsList!!.add(Interests("Emotional", false))
        interestsList!!.add(Interests("Comedy", false))
        interestsList!!.add(Interests("Bollywood", false))
        interestsList!!.add(Interests("Beauty & Style", false))
        interestsList!!.add(Interests("Entertainment", false))
        interestsList!!.add(Interests("Food", false))
        interestsList!!.add(Interests("Animals", false))
        interestsList!!.add(Interests("Tollywood", false))
        interestsList!!.add(Interests("Kollywood", false))
        interestsList!!.add(Interests("Motivation", false))
        interestsList!!.add(Interests("Learning", false))
        interestsList!!.add(Interests("Arts & Crafts", false))
        interestsList!!.add(Interests("Nature", false))
        interestsList!!.add(Interests("WildLife", false))
        interestsList!!.add(Interests("Holidays", false))
        interestsList!!.add(Interests("Pets", false))
        interestsList!!.add(Interests("Indian", false))
        interestsList!!.add(Interests("Asian", false))
        interestsList!!.add(Interests("Tourists", false))
        interestsList!!.add(Interests("Beach", false))
        interestsList!!.add(Interests("Adventure", false))
        interestsList!!.add(Interests("Sports", false))
        interestsList!!.add(Interests("Athletics", false))
        interestsList!!.add(Interests("NightLife", false))
        interestsList!!.add(Interests("Travel", false))
        interestsList!!.add(Interests("Party", false))
        interestsList!!.add(Interests("Trending", false))
        interestsList!!.add(Interests("Anime", false))
        interestsList!!.add(Interests("Manga", false))
        interestsList!!.add(Interests("Vegan", false))
        interestsList!!.add(Interests("Retro", false))
        interestsList!!.add(Interests("Mountains", false))
        interestsList!!.add(Interests("Summer", false))
        interestsList!!.add(Interests("Rainy", false))

    }




   override fun onInterestsClicked(position: Int, interests: Interests?) {
        val updatedactivities: Interests
        if (interests!!.isSelected) {
            updatedactivities = Interests(interests.interests, false)
        } else {
            updatedactivities = Interests(interests.interests, true)
        }
        interestsList!![position] = updatedactivities
        adapter?.notifyItemChanged(position)
    }
}