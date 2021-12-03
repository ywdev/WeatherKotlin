package com.wang.weather.data

import com.wang.weather.ChooseAreaModelFactory

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/2
 * @packagename： com.wang.weather.data
 */
object InjectorUtil {

    private fun getPlaceRepository() = PlaceRepository.getInstance();

    fun getChooseAreaModelFactory() = ChooseAreaModelFactory(getPlaceRepository())
}