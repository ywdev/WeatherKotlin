package com.wang.weather.ui.area

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wang.weather.WeatherApplication
import com.wang.weather.data.PlaceRepository
import com.wang.weather.data.model.City
import com.wang.weather.data.model.Country
import com.wang.weather.data.model.Province
import com.wang.weather.ui.area.MainActivity.Companion.LEVEL_CITY
import com.wang.weather.ui.area.MainActivity.Companion.LEVEL_COUNTY
import com.wang.weather.ui.area.MainActivity.Companion.LEVEL_PROVINCE
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

    var selectedProvince: Province? = null

    var selectedCity: City? = null

    lateinit var provinces: MutableList<Province>

    lateinit var cities : MutableList<City>

    lateinit var counties : MutableList<Country>


    fun onItemClick(position: Int){
        when (currentLevel.value) {
            LEVEL_PROVINCE -> {
                selectedProvince = provinces[position]
                getCities()
            }
            LEVEL_CITY -> {
                selectedCity = cities[position]
                getCountries()
            }
            LEVEL_COUNTY -> {
                //TODO
            }
        }
    }

    fun onBack(){
        if (currentLevel.value == LEVEL_COUNTY) {
            getCities()
        } else if (currentLevel.value == LEVEL_CITY) {
            getProvinces()
        }
    }


    fun getProvinces(){
        currentLevel.value = LEVEL_PROVINCE
        launch {
            provinces = repository.getProvinceList()
            dataList.addAll(provinces.map { it.provinceName })
        }
    }

    private fun getCities() = selectedProvince?.let {
        currentLevel.value = LEVEL_CITY
        launch {
            cities = repository.getCityList(it.provinceCode)
            dataList.addAll(cities.map { it.cityName })
        }
    }

    private fun getCountries() = selectedCity?.let {
        currentLevel.value = LEVEL_COUNTY
        launch {
            counties = repository.getCountryList(it.provinceId, it.cityCode)
            dataList.addAll(counties.map { it.countyName })
        }
    }

    private fun launch(block : suspend ()-> Unit) = viewModelScope.launch {
        try {
            isLoading.value = true
            dataList.clear()
            block.invoke()
            dataChanged.value = dataChanged.value?.plus(1)
            isLoading.value = false
        } catch (t: Throwable) {
            t.printStackTrace()
            Toast.makeText(WeatherApplication.context, t.message, Toast.LENGTH_SHORT).show()
            Log.d("yaowang", t.message.toString())
            dataChanged.value = dataChanged.value?.plus(1)
            isLoading.value = false
        }
    }

}