package com.wang.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wang.weather.data.PlaceRepository

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/2
 * @packagename： com.wang.weather
 */
class ChooseAreaModelFactory(private val repository: PlaceRepository) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChooseAreaViewModel(repository) as T
    }


}