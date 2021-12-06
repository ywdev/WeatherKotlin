package com.wang.weather.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/6
 * @packagename： com.wang.weather.data.model
 */
@Entity(tableName = "city")
class City(
    @SerializedName("name") val cityName: String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") val cityCode: Int){
    var provinceId = 0
}