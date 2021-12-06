package com.wang.weather.data.network

import com.wang.weather.data.model.Province
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/3
 * @packagename： com.wang.weather.data.network
 */
interface PlaceService {

    @GET("api/china")
    fun getProvinces() : Call<MutableList<Province>>

}