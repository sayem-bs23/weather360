package com.example.weather

import android.content.Context
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import android.content.Context.CONNECTIVITY_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.net.ConnectivityManager
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_ten_days.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList
import kotlin.math.roundToInt


data class Weather(val date:String, val day: String, val temperature:String, val foreCast: ForeCast){
    fun getForecastImageSource(): Int{
        when(foreCast){
            ForeCast.Clear -> return R.mipmap.sunny
            ForeCast.Clouds -> return R.mipmap.partly_cloudy
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
    weatherList.add(Weather("January 9", "tuesday", "29", ForeCast.Clouds))
    weatherList.add(Weather("January 10", "wednesday", "22", ForeCast.Clear))
    weatherList.add(Weather("January 11", "thursday", "25", ForeCast.Rain))

    return weatherList_dataSource
}


val monthMapper = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

enum class ForeCast(val forecastValue: String) {
    Clear("Clear"),
    Clouds("Clouds"),
    Rain("Rain"),
    Unknown("Unknown")
}


fun validateForecast(str:String):String{
    if(str != "Clear" && str != "Clouds" && str != "Rain"){
        return "Unknown"
    }
    return str
}

object PersistentData{
    val weatherListUpdated = ArrayList<Weather>()
}
class WeatherContent(val apiListener: APIListener){


     fun getForecast() {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        val retrofit = RetrofitSingleton.forecastInstance
        val call = retrofit.getTenDaysForecastData(
            TenDaysFragment.lat,
            TenDaysFragment.lon,
            TenDaysFragment.AppId,
            TenDaysFragment.units
        )



        call.enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(call: Call<ForecastResponse>, response: Response<ForecastResponse>) {

                Log.d("response", "dbg")
                if (response.code() == 200) {
                    val forecastResponse = response.body()!!

                    val x = forecastResponse.dayList[0].main!!.temp
                    Log.d("frval", x.toString())

                    //update after fetching the data


                    lateinit var weatherList:ArrayList<Weather>
                    weatherList = ArrayList<Weather>()


                    val dayMapper = listOf<String>("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Saturday")
                    forecastResponse.dayList.forEachIndexed { idx, weatherResponse ->
                        if(idx > 7){
                            return@forEachIndexed
                        }

                        val date = monthMapper[Date().month].toString() + " "+ getDate(idx)
                        val day = dayMapper[(Date().day + idx)%7].toString()
                        val temp = weatherResponse.main!!.temp.roundToInt().toString()
                        val forecast = validateForecast(weatherResponse.WeatherAPI[0]!!.main.toString() )

                        weatherList.add( Weather(date,
                            day,
                            temp,
                            ForeCast.valueOf(forecast))
                        )

//                        weatherList_dataSource = weatherList

                    }

                    PersistentData.weatherListUpdated.clear()
                    PersistentData.weatherListUpdated.addAll(weatherList)

                    apiListener.onAPISuccess(weatherList)


//                    weather_recycler_view.adapter = CustomAdapter(weatherList!!, ::weatherItemClicked)  //register click handler with the adapter


                }
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
//                weatherData!!.text = t.message
            }
        })
    }

    fun getDate(idx:Int):String{
        val d = Date()

        return  (d.date+idx).toString()
    }
}