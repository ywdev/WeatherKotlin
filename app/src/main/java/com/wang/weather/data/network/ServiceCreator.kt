package com.wang.weather.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/3
 * @packagename： com.wang.weather.data.network
 */
object ServiceCreator {

    private const val BASE_URL = "http://guolin.tech/"
    private val httpClient = OkHttpClient.Builder()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient.build())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass : Class<T>) : T = retrofit.create(serviceClass)

}