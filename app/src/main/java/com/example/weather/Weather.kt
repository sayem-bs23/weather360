package com.example.weather

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


enum class ForeCast(val forecastValue: String) {
    SUNNY("sunny"),
    PARTLY_CLOUD("partly_cloud"),
    RAIN_HEAVY("rain_heavy")
}

