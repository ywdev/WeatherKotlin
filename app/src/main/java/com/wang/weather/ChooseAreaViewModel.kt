package com.wang.weather

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wang.weather.MainActivity.Companion.LEVEL_PROVINCE
import com.wang.weather.data.PlaceRepository
import com.wang.weather.data.Province
import kotlinx.coroutines.launch
import java.util.*

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/2
 * @packagename： com.wang.weather
 */
class ChooseAreaViewModel(var repository: PlaceRepository) : ViewModel() {

    var currentLevel = MutableLiveData<Int>()

    var dataChanged = MutableLiveData<Int>()

    var isLoading = MutableLiveData<Boolean>()

    val dataList = ArrayList<String>()

    lateinit var provinces: MutableList<Province>



    fun getProvinces(){
        currentLevel.value = LEVEL_PROVINCE
        launch {
            provinces = repository.getProvinceList()
            dataList.addAll(provinces.map { it.provinceName })
        }
    }

    fun launch(block : suspend ()-> Unit) = viewModelScope.launch {
        try {
            isLoading.value = true
            dataList.clear()
            block.invoke()
            dataChanged.value = dataChanged.value?.plus(1)
            isLoading.value = false
        } catch (t: Throwable) {
            t.printStackTrace()
            Toast.makeText(WeatherApplication.context, t.message, Toast.LENGTH_SHORT).show()
            dataChanged.value = dataChanged.value?.plus(1)
            isLoading.value = false
        }



    }

}