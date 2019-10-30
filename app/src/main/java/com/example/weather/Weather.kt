package com.example.weather

import android.content.Context
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import android.content.Context.CONNECTIVITY_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.net.ConnectivityManager



data class Weather(val date:String, val day: String, val temperature:String, val foreCast: ForeCast){
    fun getForecastImageSource(): Int{
        when(foreCast){
            ForeCast.SUNNY -> return R.mipmap.sunny
            ForeCast.PARTLY_CLOUD -> return R.mipmap.partly_cloudy
            ForeCast.RAIN_HEAVY-> return R.mipmap.rain_heavy
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

fun loadDataFromApi(): ArrayList<Weather> {
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


val monthMapper = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

enum class ForeCast(val forecastValue: String) {
    SUNNY("Sunny"),
    PARTLY_CLOUD("Partly Cloud"),
    RAIN_HEAVY("Rain Heavy")
}


