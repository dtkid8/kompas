package com.example.kompas.repository.model

import com.squareup.moshi.Json

data class WeatherResponse (
//    @field:Json( name = "name")
    val name: String, //,
//    @field:Json( name = "weather")
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val sys: Sys
)

data class Weather(
//    @field:Json( name = "main")
    val main: String
)

data class Main(
   // @field:Json( name = "temp")
    val temp: Float,
   @Json( name = "temp_min")
    val tempMin: Float,
    @Json( name = "temp_max")
    val tempMax: Float,
    val pressure: Double,
    val humidity: Double
)

data class Wind(
    val speed: Double
)

data class Sys(
    val sunrise: Long,
    val sunset: Long
)