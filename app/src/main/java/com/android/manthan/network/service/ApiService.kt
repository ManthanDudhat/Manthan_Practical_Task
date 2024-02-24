package com.android.manthan.network.service

import com.android.manthan.model.Therapy
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/api/doctor/therapies")
    fun getData(): Call<Therapy>
}