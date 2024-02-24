package com.android.manthan.network.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.manthan.model.Therapy
import com.android.manthan.network.Singleton
import com.android.manthan.network.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {

    private var therapyList: MutableLiveData<Therapy>? = MutableLiveData()
    private val baseUrl = "https://mindmyscape.localserverpro.com"

    fun getUserData(): MutableLiveData<Therapy>? {
        Singleton.getClient(baseUrl).create(ApiService::class.java).getData()
            .enqueue(object : Callback<Therapy> {
                override fun onResponse(call: Call<Therapy>, response: Response<Therapy>) {
                    if (response.isSuccessful) {
                        Log.e("APIData", "onResponse: ${response.body()}")
                        therapyList?.postValue(response.body())
                    } else {
                        Log.e("APIData", "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<Therapy>, t: Throwable) {
                    Log.e("APIData", "onResponse: ${t.message}")
                }
            })
        return therapyList
    }
}
