package com.example.weather

import android.content.Context
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import android.content.Context.CONNECTIVITY_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.net.ConnectivityManager
import kotlin.collections.ArrayList


data class Weather(val date:String, val day: String, val temperature:String, val foreCast: ForeCast){
    fun getForecastImageSource(): Int{
        when(foreCast){
            ForeCast.Clear -> return R.mipmap.sunny
            ForeCast.Cloud -> return R.mipmap.partly_cloudy
            ForeCast.Rain-> return R.mipmap.rain_heavy
        }

        return R.drawable.ic_launcher_foreground
    }


}

class RealTimeUpdate(){
    lateinit var temperature:Number

    init {
        temperature = loadLocalStorageTemperature()

    }



    fun loadLocalStorageTemperature():Number{
        var temperature = 23
        //do your local implementation
        return temperature
    }
}

    var weatherList_dataSource = ArrayList<Weather>()
    fun loadDataFromApi(): ArrayList<Weather> {
        //api format:  xyz.com/date/
        //return  json like "{ "7/10/2019" : 23}"
        val weatherList = ArrayList<Weather>()
        weatherList.add(Weather("January 7", "sunday", "23", ForeCast.Clear))
        weatherList.add(Weather("January 8", "monday", "21", ForeCast.Rain))
        weatherList.add(Weather("January 9", "tuesday", "29", ForeCast.Cloud))
        weatherList.add(Weather("January 10", "wednesday", "22", ForeCast.Clear))
        weatherList.add(Weather("January 11", "thursday", "25", ForeCast.Rain))

        return weatherList_dataSource
    }


val monthMapper = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

enum class ForeCast(val forecastValue: String) {
    Clear("Clear"),
    Cloud("Cloud"),
    Rain("Rain"),
    Unknown("Unknown")
}


fun validateForecast(str:String):String{
    if(str != "Clear" && str != "Cloud" && str != "Rain"){
        return "Unknown"
    }
    return str
}
