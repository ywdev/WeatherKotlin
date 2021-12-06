package com.wang.weather.ui.area

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wang.weather.util.InjectorUtil
import com.wang.weather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var progressDialog: ProgressDialog? = null
    private val mAdapter by lazy { ChooseAreaAdapter(this, viewModel.dataList) }
    private val viewModel by lazy { ViewModelProvider(this, InjectorUtil.getChooseAreaModelFactory()).get(
        ChooseAreaViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = mAdapter
        binding.backButton.setOnClickListener {
            viewModel.onBack()
        }
        mAdapter.setOnItemClickListener(object : ChooseAreaAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                viewModel.onItemClick(position)
            }
        })
        viewModel.currentLevel.observe(this, { level ->
            when(level){
                LEVEL_PROVINCE -> {
                    binding.titleText.text = "中国"
                    binding.backButton.visibility = View.GONE
                }
                LEVEL_CITY -> {
                    binding.titleText.text = viewModel.selectedProvince?.provinceName
                    binding.backButton.visibility = View.VISIBLE
                }
                LEVEL_COUNTY -> {
                    binding.titleText.text = viewModel.selectedCity?.cityName
                    binding.backButton.visibility = View.VISIBLE
                }
            }
        })
        viewModel.dataChanged.observe(this, Observer {
            mAdapter.notifyDataSetChanged()
            Log.d("yaowang","end:"+System.currentTimeMillis())
        })
        viewModel.isLoading.observe(this, Observer {
            if(it){
                showProgressDialog()
            }else{
                closeProgressDialog()
            }
        })
        if (viewModel.dataList.isEmpty()){
            viewModel.getProvinces()
        }
    }

    /**
     * 显示进度对话框
     */
    private fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
            progressDialog?.setMessage("正在加载...")
            progressDialog?.setCanceledOnTouchOutside(false)
        }
        progressDialog?.show()
    }

    /**
     * 关闭进度对话框
     */
    private fun closeProgressDialog() {
        progressDialog?.dismiss()
    }


    companion object {
        const val LEVEL_PROVINCE = 0
        const val LEVEL_CITY = 1
        const val LEVEL_COUNTY = 2
    }

    override fun onBackPressed() {
        if(!viewModel.onBack()){
            super.onBackPressed()
        }
    }

}