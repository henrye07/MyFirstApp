package com.darkaxce.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TourPlaceActivity : AppCompatActivity() {

    val listTour:List<TourPlace> = listOf(
        TourPlace("https://loremflickr.com/450/450","name1","description1", 4.0F),
        TourPlace("https://loremflickr.com/450/450","name2","description2",5.0F),
        TourPlace("https://loremflickr.com/450/450","name3","description3",3.5F),
        TourPlace("https://loremflickr.com/450/450","name4","description4",2.5F),
        TourPlace("https://loremflickr.com/450/450","name5","description5",1.5F),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tour_place)
        initRecycler()
    }

    fun initRecycler(){
        val recycler: RecyclerView = findViewById(R.id.listTourPlace)
        recycler.layoutManager=LinearLayoutManager(this)
        val adapter= TourPlaceAdapter(listTour)
        recycler.adapter=adapter
    }
    //
}