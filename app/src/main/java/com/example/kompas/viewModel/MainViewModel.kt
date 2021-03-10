package com.example.kompas.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kompas.repository.model.WeatherResponse
import com.example.kompas.repository.service.API_KEY
import com.example.kompas.repository.service.BASE_URL
import com.example.kompas.repository.service.WeatherService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class MainViewModel : ViewModel(){
    private val weather = MutableLiveData<WeatherResponse>()


    fun setWeather(city: String) {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        val service = retrofit.create(WeatherService::class.java)

        service.getWeather(city, "metric",API_KEY).enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Timber.e("EXCEPTION : $t")
            }

            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    Timber.d( "Response : ${response.body().toString()}")
                    weather.postValue(response.body())
                }
            }

        })
    }

    fun getWeather(): LiveData<WeatherResponse> {
        return weather
    }

}