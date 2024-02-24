package com.android.manthan.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Singleton {

    fun getClient(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}