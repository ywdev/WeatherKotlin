package com.wang.weather.data

import com.wang.weather.data.network.PlaceService
import com.wang.weather.data.network.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/2
 * @packagename： com.wang.weather.data
 */
class WeatherNetWork {

    private val placeService = ServiceCreator.create(PlaceService::class.java)

    suspend fun fetchProvinceList() = placeService.getProvinces().await()

    suspend fun fetchCityList(provinceId : Int) = placeService.getCities(provinceId).await()

    suspend fun fetchCountryList(provinceId: Int, cityId: Int) = placeService.getCounties(provinceId, cityId).await()

    private suspend fun <T> Call<T>.await() : T {
        return suspendCoroutine {
            enqueue(object : Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body!=null){
                        it.resume(body)
                    }else{
                        it.resumeWithException(RuntimeException("response body is null"))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    it.resumeWithException(t)
                }
            })
        }
    }

    companion object {

        private var netWork : WeatherNetWork? = null

        fun getInstance() : WeatherNetWork{
            if(netWork==null){
                synchronized(WeatherNetWork::class.java){
                    if(netWork == null){
                        netWork = WeatherNetWork()
                    }
                }
            }
            return netWork!!
        }
    }

}