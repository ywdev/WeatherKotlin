package com.wang.weather.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wang.weather.data.model.City
import com.wang.weather.data.model.Country
import com.wang.weather.data.model.Province

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/2
 * @packagename： com.wang.weather.data
 */
@Dao
interface PlaceDao {

    @Insert
    fun insertProvince(list: MutableList<Province>)

    @Query("select * from province order by provinceCode asc")
    fun getProvinceList() : MutableList<Province>

    @Insert
    fun insertCity(list: MutableList<City>)

    @Query("select * from city where provinceId = :provinceId order by cityCode asc")
    fun getCityList(provinceId : Int) : MutableList<City>

    @Insert
    fun insertCountry(list: MutableList<Country>)

    @Query("select * from country where cityId = :cityId")
    fun getCountryList(cityId : Int) : MutableList<Country>

}