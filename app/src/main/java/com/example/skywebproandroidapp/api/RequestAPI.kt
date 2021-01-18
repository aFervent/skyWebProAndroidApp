package com.example.skywebproandroidapp.api

import com.example.skywebproandroidapp.model.PhotoItem
import com.example.skywebproandroidapp.model.WeatherItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestAPI {

    @GET("/v2/list?page=2&limit=100")
    suspend fun getPhoto(): Response<PhotoItem>

    @GET("/data/2.5/weather?q=Moscow&appid=c35880b49ff95391b3a6d0edd0c722eb")
    suspend fun getWeather(): Response<WeatherItem>
}