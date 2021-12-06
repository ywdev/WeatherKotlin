package com.wang.weather.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wang.weather.WeatherApplication
import com.wang.weather.data.model.City
import com.wang.weather.data.model.Country
import com.wang.weather.data.model.Province

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/3
 * @packagename： com.wang.weather.data
 */
@Database(entities = [Province::class, City::class, Country::class], version = 1)
abstract class LocalDataBase : RoomDatabase() {

    abstract fun getPlaceDao(): PlaceDao

    companion object {

        private var instance: LocalDataBase? = null

        fun getInstance(): LocalDataBase {
            if (instance == null) {
                synchronized(LocalDataBase::class.java) {
                    if (instance == null) {
                        instance = Room
                            .databaseBuilder(WeatherApplication.context,LocalDataBase::class.java,"localDataBase.db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return instance!!
        }
    }
}