package com.example.covidtracker.api

import com.example.covidtracker.util.constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SuperheroInstant {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(constant.ALL_HERO_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiClient by lazy {
        retrofit.create(ApiClient::class.java)
    }
}