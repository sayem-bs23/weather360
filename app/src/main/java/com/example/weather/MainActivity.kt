package com.example.weather

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_ten_days.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        RealTimeUpdate()


        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(TodayFragment(), "Today")
        adapter.addFragment(TenDaysFragment(), "Ten days")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

    }



}


