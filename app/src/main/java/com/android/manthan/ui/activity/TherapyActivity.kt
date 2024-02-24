package com.android.manthan.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.android.manthan.databinding.ActivityTherapyBinding
import com.android.manthan.ui.adapter.TherapyAdapter
import com.android.manthan.utils.isVisible
import com.android.manthan.viewmodel.MainViewModel

class TherapyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTherapyBinding
    private val therapyAdapter = TherapyAdapter()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTherapyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.loadData()
        observeLiveData()
        setRecyclerView()
    }

    private fun setRecyclerView() = with(binding.rvDetails) {
        layoutManager = GridLayoutManager(this@TherapyActivity,3)
        adapter = therapyAdapter
    }

    private fun observeLiveData() = with(binding){
        mainViewModel.getUserLiveData.observe(this@TherapyActivity) { data ->
            progressBar.isVisible(false)
            rvDetails.isVisible(true)
            if (data!=null) {
                therapyAdapter.therapyList = data.data ?: arrayListOf()
                therapyAdapter.notifyItemRangeChanged(0, therapyAdapter.therapyList.size)
            }
        }
    }
}