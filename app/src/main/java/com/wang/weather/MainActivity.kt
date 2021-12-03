package com.wang.weather

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wang.weather.data.InjectorUtil
import com.wang.weather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mAdapter by lazy { ChooseAreaAdapter(this, viewModel.dataList) }
    private val viewModel by lazy { ViewModelProvider(this, InjectorUtil.getChooseAreaModelFactory()).get(ChooseAreaViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = mAdapter
        viewModel.currentLevel.observe(this, { level ->
            when(level){
                LEVEL_PROVINCE -> {
                    binding.titleText.text = "中国"
                    binding.backButton.visibility = View.GONE
                }
            }
        })
        viewModel.dataChanged.observe(this, Observer {
            mAdapter.notifyDataSetChanged()
        })
        if (viewModel.dataList.isEmpty()){
            viewModel.getProvinces()
        }
    }


    companion object {
        const val LEVEL_PROVINCE = 0
        const val LEVEL_CITY = 1
        const val LEVEL_COUNTY = 2
    }

}