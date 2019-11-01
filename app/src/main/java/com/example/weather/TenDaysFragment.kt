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

        getForecast()

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

    internal fun getForecast() {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        val retrofit = RetrofitSingleton.forecastInstance
        val call = retrofit.getTenDaysForecastData(lat, lon, AppId, units)



        call.enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(call: Call<ForecastResponse>, response: Response<ForecastResponse>) {

                Log.d("response", "dbg")
                if (response.code() == 200) {
                    val forecastResponse = response.body()!!

                    val x = forecastResponse.dayList[0].main!!.temp
                    Log.d("frval", x.toString())

                    //update after fetching the data
                    weather_recycler_view.layoutManager = LinearLayoutManager(context)

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

                        weatherList_dataSource = weatherList
                    }

                    weather_recycler_view.adapter = CustomAdapter(weatherList!!, ::weatherItemClicked)  //register click handler with the adapter


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

    fun getDay(idx:Int):String{
        val d = Date()
        return  monthMapper[d.month].toString()
    }



}

