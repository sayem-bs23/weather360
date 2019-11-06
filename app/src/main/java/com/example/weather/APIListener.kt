package com.example.weather

interface APIListener {
    fun onAPICalled()
    fun onAPISuccess(weatherList: ArrayList<Weather>)
    fun onAPIFailed()
    fun onAPIConsumed()
}