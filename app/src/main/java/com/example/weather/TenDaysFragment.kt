package com.example.weather

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_ten_days.*
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Constructor


class TenDaysFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View? {
        var temp = inflater.inflate(R.layout.fragment_ten_days, container, false)!!
        return temp
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weather_recycler_view.layoutManager = LinearLayoutManager(context)

        //load data from api/data-source
        val weatherList = loadDataFromApi()
        weather_recycler_view.adapter = CustomAdapter(weatherList, ::weatherItemClicked)  //register click handler with the adapter


    }


    private fun weatherItemClicked(weather: Weather, pos:Int) {
        val cat = listOf<String>("cat")
        Toast.makeText(context, "Clicked: ${weather.day}", Toast.LENGTH_LONG).show()
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("position", pos)

        startActivity(intent);
    }


}

