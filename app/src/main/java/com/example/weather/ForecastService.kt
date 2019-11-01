package com.example.weather


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    @GET("data/2.5/forecast/?")
    fun getTenDaysForecastData(@Query("lat") lat: String,
                              @Query("lon") lon: String,
                              @Query("APPID") app_id: String,
                              @Query("units") units: String

    ): Call<ForecastResponse>
}

