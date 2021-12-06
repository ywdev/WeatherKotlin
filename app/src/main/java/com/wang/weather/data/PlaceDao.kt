package com.wang.weather.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
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
    suspend fun insertProvince(list: MutableList<Province>)

    @Query("select * from province order by provinceCode asc")
    suspend fun getProvinceList() : MutableList<Province>



}