package com.example.weather

import android.content.Context
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
        weather_recycler_view.adapter = CustomAdapter(weatherList, {weather: Weather -> weatherItemClicked(weather)})  //register click handler with the adapter


    }
    fun loadDataFromApi():ArrayList<Weather> {
        //api format:  xyz.com/date/
        //return  json like "{ "7/10/2019" : 23}"
        val weatherList = ArrayList<Weather>()
        weatherList.add(Weather("January 7", "sunday", "23", ForeCast.SUNNY))
        weatherList.add(Weather("January 8", "monday", "21", ForeCast.RAIN_HEAVY))
        weatherList.add(Weather("January 9", "tuesday", "29", ForeCast.PARTLY_CLOUD))
        weatherList.add(Weather("January 10", "wednesday", "22", ForeCast.SUNNY))
        weatherList.add(Weather("January 11", "thursday", "25", ForeCast.RAIN_HEAVY))

        return weatherList
    }

    private fun weatherItemClicked(weather: Weather) {
        val cat = listOf<String>("cat")
        Toast.makeText(context, "Clicked: ${weather.day}", Toast.LENGTH_LONG).show()
    }


}

