package com.wang.weather.data

import com.wang.weather.data.model.City
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

    suspend fun getCityList(provinceId : Int) = withContext(Dispatchers.IO){
        var cityList = placeDao.getCityList(provinceId)
        if(cityList.isEmpty()){
            cityList = netWork.fetchCityList(provinceId)
            cityList.forEach { it.provinceId = provinceId }
            placeDao.insertCity(cityList)
        }
        cityList
    }

    suspend fun getCountryList(provinceId: Int, cityId : Int) = withContext(Dispatchers.IO){
        var countryList = placeDao.getCountryList(cityId)
        if(countryList.isEmpty()){
            countryList = netWork.fetchCountryList(provinceId, cityId)
            countryList.forEach { it.cityId = cityId }
            placeDao.insertCountry(countryList)
        }
        countryList
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