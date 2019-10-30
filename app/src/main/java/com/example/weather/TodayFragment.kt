package com.example.weather

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_today.*
import java.util.*

class TodayFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_today, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val d = Date()
        dateAndTimeTop.text =  "${d.date.toString()} ${monthMapper[d.month].toString()},"
        temperature.text = RealTimeUpdate().temperature.toString() + "°"


    }


}
