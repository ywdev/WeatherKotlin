package com.wang.weather.data.network

import com.wang.weather.data.model.City
import com.wang.weather.data.model.Country
import com.wang.weather.data.model.Province
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/3
 * @packagename： com.wang.weather.data.network
 */
interface PlaceService {

    @GET("api/china")
    fun getProvinces() : Call<MutableList<Province>>

    @GET("api/china/{provinceId}")
    fun getCities(@Path("provinceId") provinceId: Int) : Call<MutableList<City>>

    @GET("api/china/{provinceId}/{cityId}")
    fun getCounties(@Path("provinceId") provinceId: Int, @Path("cityId") cityId: Int) : Call<MutableList<Country>>


}