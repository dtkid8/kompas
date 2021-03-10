package com.example.kompas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kompas.R
import com.example.kompas.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate called")
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide()

        mainViewModel = ViewModelProvider(
                this, ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
        mainViewModel.setWeather("jakarta")
        textView.text = "Jakarta"
        showLoading(true)
        mainViewModel.getWeather().observe(this, Observer { weatherResp ->
            if (weatherResp != null) {
                showLoading(false)
                var condition: String
                if (weatherResp.weather.count() > 0) condition =
                    weatherResp.weather[0].main.toString()
                else condition = ""
                textView3.text = condition
                textView5.text = weatherResp.main.temp.toInt().toString() + "°C"
                textView6.text = "Min Temp: " + weatherResp.main.tempMin.toInt().toString() + "°C"
                textView4.text = "Max Temp: " + weatherResp.main.tempMax.toInt().toString() + "°C"
                pressureTextView.text = weatherResp.main.pressure.toString()
                humidityTextView.text = weatherResp.main.humidity.toString()
                windTextView.text = weatherResp.wind.speed.toInt().toString()
                sunriseTextView.text = convertLongToTime(weatherResp.sys.sunrise.toLong())
                sunsetTextView.text = convertLongToTime(weatherResp.sys.sunset.toLong())
            } else {
                showLoading(false)
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBarDetail.visibility = View.VISIBLE
        } else {
            progressBarDetail.visibility = View.GONE
        }
    }
    private fun convertLongToTime(time: Long): String {
        val date = Date(time*1000)
        val format = SimpleDateFormat("hh:mm a")
        return format.format(date)
    }

}