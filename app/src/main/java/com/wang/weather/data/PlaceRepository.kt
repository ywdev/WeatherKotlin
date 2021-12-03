package com.wang.weather.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/2
 * @packagename： com.wang.weather.data
 */
class PlaceRepository {

    suspend fun getProvinceList() = withContext(Dispatchers.IO){
        var list = mutableListOf<Province>()
        list.add(Province("湖北","001"))
        list.add(Province("广东","002"))
//        list.add(Province("湖北", "001"))
//        list.add(Province("广东", "002"))
        list
    }

    companion object {

        private var instance: PlaceRepository? = null

        fun getInstance(): PlaceRepository {
            if (instance == null) {
                synchronized(PlaceRepository::class.java) {
                    if (instance == null) {
                        instance = PlaceRepository()
                    }
                }
            }
            return instance!!
        }

    }
}