package com.wang.weather.util

import com.wang.weather.data.LocalDataBase
import com.wang.weather.data.PlaceDao
import com.wang.weather.data.PlaceRepository
import com.wang.weather.data.WeatherNetWork
import com.wang.weather.ui.area.ChooseAreaModelFactory

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/2
 * @packagename： com.wang.weather.data
 */
object InjectorUtil {

    private fun getPlaceRepository() = PlaceRepository.getInstance(WeatherNetWork.getInstance());

    fun getChooseAreaModelFactory() = ChooseAreaModelFactory(getPlaceRepository())
}