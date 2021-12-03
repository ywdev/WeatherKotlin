package com.wang.weather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/2
 * @packagename： com.wang.weather
 */
class WeatherApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context : Context
    }


}