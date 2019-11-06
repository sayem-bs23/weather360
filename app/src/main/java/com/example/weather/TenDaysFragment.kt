package com.example.weather

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Constructor
import java.util.*
import kotlin.collections.ArrayList
import com.example.weather.monthMapper
import kotlin.math.roundToInt


class TenDaysFragment: Fragment(), APIListener{
    val weatherContent = WeatherContent(this)
    val customAdapter = CustomAdapter(ArrayList<Weather>(), ::weatherItemClicked)

    override fun onAPICalled() {

    }

    override fun onAPISuccess(weatherList: ArrayList<Weather>) {

        customAdapter.setData(weatherList)
    }

    override fun onAPIFailed() {

    }

    override fun onAPIConsumed() {

    }

    fun setupAdapter(){

    }

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
        weather_recycler_view.adapter = customAdapter
        weatherContent.getForecast()

        //getForecast()

    }


    private fun weatherItemClicked(weather: Weather, pos:Int) {
        val cat = listOf<String>("cat")
        Toast.makeText(context, "Clicked: ${weather.day}", Toast.LENGTH_LONG).show()
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("position", pos)

        startActivity(intent);
    }


    companion object{
        val AppId= "bb4dd20e432ae754a84c5b91ee475854"
        val lat = "23"
        val lon = "90"
        val units = "metric"
    }





    fun getDay(idx:Int):String{
        val d = Date()
        return  monthMapper[d.month].toString()
    }



}

