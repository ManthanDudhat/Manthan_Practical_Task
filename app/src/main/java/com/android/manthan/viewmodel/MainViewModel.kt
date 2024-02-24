package com.android.manthan.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.manthan.model.MachineTest
import com.android.manthan.model.Therapy
import com.android.manthan.network.repository.MainRepository
import com.android.manthan.ui.adapter.MachineTestAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("NotifyDataSetChanged")
class MainViewModel : ViewModel() {

    private var repository: MainRepository = MainRepository()
    val getUserLiveData by lazy { MutableLiveData<Therapy>() }
    var machineTestLeftList = ArrayList<MachineTest>()
    var machineTestRightList = ArrayList<MachineTest>()


    fun loadData() {
        viewModelScope.launch {
            val response = repository.getUserData()
            delay(2000)
            getUserLiveData.postValue(response?.value)
            Log.d("LiveData", "loadData: ${response?.value}")
            Log.d("LiveData", "loadData: ${getUserLiveData.value}")
        }
    }

    fun getMachineData() {
        if (machineTestLeftList.isEmpty() && machineTestRightList.isEmpty()) {
            machineTestLeftList.add(MachineTest("A"))
            machineTestLeftList.add(MachineTest("B"))
            machineTestLeftList.add(MachineTest("C"))
            machineTestLeftList.add(MachineTest("D"))
            machineTestLeftList.add(MachineTest("E"))
            machineTestLeftList.add(MachineTest("F"))
            machineTestLeftList.add(MachineTest("G"))
            machineTestLeftList.add(MachineTest("H"))
            machineTestLeftList.add(MachineTest("I"))
            machineTestLeftList.add(MachineTest("J"))
        }
    }

    fun machineDataIncrement(data: MachineTest, machineTestAdapter: MachineTestAdapter, position: Int) {
        data.count += 1
        machineTestAdapter.notifyItemChanged(position)
    }

    fun machineDataDecrement(data: MachineTest, machineTestAdapter: MachineTestAdapter, position: Int) {
        data.count -= 1
        machineTestAdapter.notifyItemChanged(position)
    }

    fun machineDataSelection(data: MachineTest, machineTestAdapter: MachineTestAdapter, position: Int) {
        data.isSelected = data.isSelected == false
        machineTestAdapter.notifyItemChanged(position)
    }

    fun moveRight(machineTestAdapterLeft: MachineTestAdapter,
                  machineTestAdapterRight: MachineTestAdapter) {

        for (alphabet in machineTestLeftList) {
            if (alphabet.isSelected) {
                viewModelScope.launch {
                    delay(100)
                    machineTestRightList.add(alphabet)
                    machineTestLeftList.remove(alphabet)
                    machineTestAdapterRight.notifyDataSetChanged()
                    machineTestAdapterLeft.notifyDataSetChanged()
                }
                alphabet.isSelected = false
            }
        }
    }

    fun moveLeft(machineTestAdapterLeft: MachineTestAdapter,
                 machineTestAdapterRight: MachineTestAdapter) {
        for (alphabet in machineTestRightList) {
            if (alphabet.isSelected) {
                viewModelScope.launch {
                    delay(100)
                    machineTestLeftList.add(alphabet)
                    machineTestRightList.remove(alphabet)
                    machineTestAdapterRight.notifyDataSetChanged()
                    machineTestAdapterLeft.notifyDataSetChanged()
                }
                alphabet.isSelected = false
            }
        }
    }
}