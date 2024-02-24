package com.android.manthan.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.manthan.databinding.ActivityMachineTestBinding
import com.android.manthan.model.MachineTest
import com.android.manthan.ui.adapter.MachineTestAdapter
import com.android.manthan.ui.adapter.MachineTestAdapter.Companion.DECREMENT
import com.android.manthan.ui.adapter.MachineTestAdapter.Companion.INCREMENT
import com.android.manthan.ui.adapter.MachineTestAdapter.Companion.ROOT
import com.android.manthan.viewmodel.MainViewModel

class MachineTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMachineTestBinding
    private val machineTestAdapterLeft = MachineTestAdapter()
    private val machineTestAdapterRight = MachineTestAdapter()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMachineTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.getMachineData()
        setRecyclerView()
        setListeners()
    }

    private fun setRecyclerView() = with(binding) {
        setRecyclerViewWithAdapter(rvLeft, machineTestAdapterLeft, mainViewModel.machineTestLeftList)
        setRecyclerViewWithAdapter(rvRight, machineTestAdapterRight, mainViewModel.machineTestRightList)
    }

    private fun setListeners() = with(binding) {
        btnMoveRight.setOnClickListener {
            mainViewModel.moveRight(machineTestAdapterLeft, machineTestAdapterRight)
        }

        btnMoveLeft.setOnClickListener {
            mainViewModel.moveLeft(machineTestAdapterRight, machineTestAdapterLeft)
        }
    }

    private fun setRecyclerViewWithAdapter(recyclerView: RecyclerView,
                                           machineTestAdapter: MachineTestAdapter,
                                           machineTestList: ArrayList<MachineTest>) = with(recyclerView) {
        layoutManager = LinearLayoutManager(this@MachineTestActivity)
        adapter = machineTestAdapter
        machineTestAdapter.addData(machineTestList)

        machineTestAdapter.callback = { data, position, type ->
            when (type) {
                INCREMENT -> {
                    mainViewModel.machineDataIncrement(data, machineTestAdapter, position)
                }

                DECREMENT -> {
                    mainViewModel.machineDataDecrement(data, machineTestAdapter, position)
                }

                ROOT -> {
                    mainViewModel.machineDataSelection(data, machineTestAdapter, position)
                }
            }
        }
    }
}