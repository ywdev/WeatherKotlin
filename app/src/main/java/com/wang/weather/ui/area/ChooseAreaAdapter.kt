package com.wang.weather.ui.area

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wang.weather.R

/**
 * @author yaowang
 * @version 1.0
 * @date：2021/12/3
 * @packagename： com.wang.weather
 */
class ChooseAreaAdapter(private var context : Context, private var mData : List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BaseDataViewHolder(LayoutInflater.from(context).inflate(R.layout.item_area_layout,parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myHolder  = holder as BaseDataViewHolder
        myHolder.textView.text = mData[position]
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class BaseDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textView: TextView = itemView.findViewById(R.id.tv_name)

    }

}