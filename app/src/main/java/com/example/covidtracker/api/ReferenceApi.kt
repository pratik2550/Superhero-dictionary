package com.example.covidtracker.api

import com.example.covidtracker.util.constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ReferenceApi {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ReferenceSuperhero by lazy {
        retrofit.create(ReferenceSuperhero::class.java)
    }
}