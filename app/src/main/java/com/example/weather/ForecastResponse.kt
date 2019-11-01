package com.example.weather


import com.google.gson.annotations.SerializedName

class ForecastResponse {

    @SerializedName("list")
    var dayList = ArrayList<WeatherResponse>()

}

