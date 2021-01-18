package com.example.skywebproandroidapp.repository

import com.example.skywebproandroidapp.api.RetrofitInstance

class WeatherRepository {

    suspend fun getWeatherItem() =
        RetrofitInstance.api_weather.getWeather()
}