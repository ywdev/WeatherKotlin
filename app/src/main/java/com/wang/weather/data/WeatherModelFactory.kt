package com.wang.weather.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/2
 * @packagename： com.wang.weather.data
 */
class WeatherModelFactory(val repository: PlaceRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }
}