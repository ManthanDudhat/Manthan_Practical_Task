package com.android.manthan.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.manthan.R
import com.android.manthan.databinding.RowMachineTestBinding
import com.android.manthan.model.MachineTest
import com.android.manthan.utils.isVisible

class MachineTestAdapter : RecyclerView.Adapter<MachineTestAdapter.ViewHolder>() {

    private var machineTestLeftList = ArrayList<MachineTest>()
    var callback: ((MachineTest, Int, String) -> Unit)? = null

    companion object {
        const val INCREMENT = "INCREMENT"
        const val DECREMENT = "DECREMENT"
        const val ROOT = "ROOT"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MachineTestAdapter.ViewHolder {
        return ViewHolder(RowMachineTestBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MachineTestAdapter.ViewHolder, position: Int) {
        holder.setData(machineTestLeftList[position])
    }

    override fun getItemCount(): Int {
        return machineTestLeftList.size
    }


    fun addData(list: ArrayList<MachineTest>) {
        machineTestLeftList.clear()
        this.machineTestLeftList = list
        notifyItemRangeChanged(0, list.size)
    }

    inner class ViewHolder(private val binding: RowMachineTestBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(data: MachineTest) = with(binding) {
            tvAlphabet.text = data.alphabet
            tvCount.text = data.count.toString()
            viewCounter.isVisible(data.isSelected)

            if (data.isSelected) {
                root.setBackgroundColor(root.context.getColor(R.color.black_50))
            } else {
                root.setBackgroundColor(root.context.getColor(R.color.white))
            }

            btnIncrement.setOnClickListener {
                callback?.invoke(data, adapterPosition, INCREMENT)
            }

            btnDecrement.setOnClickListener {
                callback?.invoke(data, adapterPosition, DECREMENT)
            }

            root.setOnClickListener {
                callback?.invoke(data, adapterPosition, ROOT)
            }
        }
    }
}