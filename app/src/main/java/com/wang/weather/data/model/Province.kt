package com.wang.weather.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/2
 * @packagename： com.wang.weather.data
 */
@Entity(tableName = "province")
class Province(
    @SerializedName("name") var provinceName : String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") var provinceCode : Int) {
}