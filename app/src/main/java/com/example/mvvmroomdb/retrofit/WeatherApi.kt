package com.example.mvvmroomdb.retrofit

import com.example.mvvmroomdb.retrofit.weatherModel.WeatherResponse
import com.example.mvvmroomdb.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("onecall")
    fun getWeatherData(@Query("lat") latitude : Double,
                       @Query("lon") longitude : Double,
                       @Query("exclude") exclude : String,
                       @Query("appid") apiKey : String = Constants.apiKey) : Call<WeatherResponse>
}