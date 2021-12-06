package com.wang.weather.data

import com.wang.weather.data.model.Province
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/2
 * @packagename： com.wang.weather.data
 */
class PlaceRepository(var placeDao: PlaceDao, var netWork: WeatherNetWork) {

    suspend fun getProvinceList() = withContext(Dispatchers.IO){
        var provinceList = placeDao.getProvinceList()
        if(provinceList.isEmpty()){
            provinceList = netWork.fetchProvinceList()
            placeDao.insertProvince(provinceList)
        }
        provinceList
    }

    companion object {

        private var instance: PlaceRepository? = null

        fun getInstance(placeDao: PlaceDao, netWork: WeatherNetWork): PlaceRepository {
            if (instance == null) {
                synchronized(PlaceRepository::class.java) {
                    if (instance == null) {
                        instance = PlaceRepository(placeDao,netWork)
                    }
                }
            }
            return instance!!
        }

    }
}