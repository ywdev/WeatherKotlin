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
@Entity(tableName = "country")
class Country(
    @SerializedName("name") val countyName: String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("weather_id") val weatherId: String
){
    var cityId = 0
}