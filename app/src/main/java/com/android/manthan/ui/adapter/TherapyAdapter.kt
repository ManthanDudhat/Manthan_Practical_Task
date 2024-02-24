package com.android.manthan.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.manthan.R
import com.android.manthan.databinding.RowTherapyDetailsBinding
import com.android.manthan.model.Therapy
import com.android.manthan.utils.setTextColorRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class TherapyAdapter : RecyclerView.Adapter<TherapyAdapter.ViewHolder>() {

    var therapyList: ArrayList<Therapy.Data> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowTherapyDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return therapyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(therapyList[position])
    }

    inner class ViewHolder(private val binding: RowTherapyDetailsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(data: Therapy.Data) = with(binding) {
            if (data.name == "procrastination") {
                tvName.setTextColorRes(R.color.black)
            }
            tvName.text = data.name
            Glide.with(binding.root.context)
                .load(data.profile)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivProfile)
        }
    }
}