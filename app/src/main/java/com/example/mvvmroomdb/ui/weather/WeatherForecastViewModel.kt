package com.example.mvvmroomdb.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmroomdb.repo.WeatherRepository
import com.example.mvvmroomdb.retrofit.weatherModel.WeatherResponse

class WeatherForecastViewModel : ViewModel() {
    private val repository : WeatherRepository = WeatherRepository()

    fun getWeatherData(lat: Double, lon: Double): LiveData<WeatherResponse> {
        repository.getWeatherData(lat, lon)
        return repository.weatherData
    }
}
