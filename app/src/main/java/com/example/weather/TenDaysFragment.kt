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

//        var weatherList = listOf<String>("cat", "dog", "cow")
//        val listViewAdapter = ArrayAdapter<String>(context!!, android.R.layout.simple_list_item_1, weatherList)
//        weather_list_view.adapter = listViewAdapter


        return temp
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        var weatherList = listOf<String>("weather_sunny", "weather_cloudy", "weather_rainy")
//        val listViewAdapter = ArrayAdapter<String>(view.context, android.R.layout.simple_list_item_1, weatherList)
//
//        weather_list_view.adapter = listViewAdapter

        weather_recycler_view.layoutManager = LinearLayoutManager(context)
        val users = ArrayList<User>()
        users.add(User("a", "bd"))
        users.add(User("b", "bd"))
        users.add(User("c", "bd"))
        users.add(User("d", "bd"))

        weather_recycler_view.adapter = CustomAdapter(users)

    }



}