package com.example.sunnyweather

import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {
    companion object {
        lateinit var context: Context
        const val TOKEN = "ugiuRL4VUd2nswhJ"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}