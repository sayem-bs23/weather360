package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.card_details.*
import java.util.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_details)


        val position = intent.extras?.get("position").toString().toInt()
        val weatherList = loadDataFromApi()
        val weather = weatherList[position]

        temperature_cardDetail.text = weather.temperature
        weatherSatus_cardDetail.text = weather.foreCast.forecastValue
        weatherAvatar_cardDetail.setImageResource(weather.getForecastImageSource())
        val d = Date()
        dateAndTimeTop_cardDetail.text = "${d.date.toString()} ${monthMapper[d.month].toString()},"






//        val adapter = ViewPagerAdapter(supportFragmentManager)
//        adapter.addFragment(TodayFragment(), "Today")
//        viewPager_cardDetail.adapter = adapter
    }
}
