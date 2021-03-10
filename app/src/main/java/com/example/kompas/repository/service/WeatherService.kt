package com.example.kompas.repository.service

import com.example.kompas.repository.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
const val API_KEY = "570adda5076c74dadb24a73d308a6a1f"
interface WeatherService {
    @GET("weather?")
    fun getWeather(@Query(value = "q") city: String,@Query(value = "units") units: String,@Query(value="appid") appId: String ): Call<WeatherResponse>
}