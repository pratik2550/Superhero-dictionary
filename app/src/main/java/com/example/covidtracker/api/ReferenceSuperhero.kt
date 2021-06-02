package com.example.covidtracker.api

import com.example.covidtracker.model.Superhero
import com.example.covidtracker.model.superheroSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ReferenceSuperhero {

    @GET("search/{hero}")
    suspend fun searchSuperhero(@Path("hero") hero: String): Response<superheroSearch>

    @GET("{id}")
    suspend fun getById(@Path("id") id:String): Response<Superhero>
}